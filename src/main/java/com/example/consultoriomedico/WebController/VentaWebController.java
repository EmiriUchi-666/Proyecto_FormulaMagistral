package com.example.consultoriomedico.WebController;

import com.example.consultoriomedico.Model.Paciente;
import com.example.consultoriomedico.Model.Usuario;
import com.example.consultoriomedico.Model.Venta;
import com.example.consultoriomedico.Model.VentaDetalle;

import com.example.consultoriomedico.Service.PacienteService;
import com.example.consultoriomedico.Service.UsuarioService;
import com.example.consultoriomedico.Service.VentaDetalleService;
import com.example.consultoriomedico.Service.VentaService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/app/ventas")
public class VentaWebController {


    private final VentaService ventaService;
    private final VentaDetalleService ventaDetalleService;
    private final PacienteService pacienteService;
    private final UsuarioService usuarioService;


    public VentaWebController(
            VentaService ventaService,
            VentaDetalleService ventaDetalleService,
            PacienteService pacienteService,
            UsuarioService usuarioService
    ) {
        this.ventaService = ventaService;
        this.ventaDetalleService = ventaDetalleService;
        this.pacienteService = pacienteService;
        this.usuarioService = usuarioService;
    }



    // LISTAR VENTAS
    @GetMapping
    public String listar(
            @RequestParam(required = false) String q,
            Model model
    ) {


        List<Map<String,Object>> ventas = ventaService.listar()
                .stream()

                .filter(v ->
                        q == null ||
                        q.isBlank() ||
                        (v.getEstado() != null &&
                        v.getEstado()
                         .toLowerCase()
                         .contains(q.toLowerCase()))
                )

                .map(this::aVista)

                .toList();



        model.addAttribute("ventas", ventas);
        model.addAttribute("q", q);


        return "ventas/list";
    }





    // FORMULARIO NUEVA VENTA
    @GetMapping("/nuevo")
    public String nuevo(Model model) {


        model.addAttribute(
                "pacientes",
                pacienteService.listar()
        );


        return "ventas/form";
    }






    // GUARDAR VENTA
    @PostMapping("/guardar")
    public String guardar(

            @RequestParam Integer idPaciente,

            @RequestParam String tipoComprobante,

            @RequestParam(required = false)
            String numComprobante,

            @RequestParam BigDecimal total,

            Authentication authentication,

            RedirectAttributes ra

    ) {



        Paciente paciente =
                pacienteService.buscar(idPaciente);



        Usuario usuarioActual =
                usuarioService.buscarPorEmail(
                        authentication.getName()
                );



        Venta venta = new Venta();



        venta.setPaciente(paciente);



        if(usuarioActual != null){
            venta.setIdUsuario(
                    usuarioActual.getIdUsuario()
            );
        }



        venta.setTipoComprobante(tipoComprobante);

        venta.setNumComprobante(numComprobante);

        venta.setTotal(total);



        ventaService.registrar(venta);



        ra.addFlashAttribute(
                "success",
                "Venta registrada correctamente"
        );



        return "redirect:/app/ventas";

    }







    // TICKET
    @GetMapping("/{id}/ticket")
    public String ticket(

            @PathVariable Integer id,

            Model model

    ) {



        Venta venta =
                ventaService.buscar(id);



        model.addAttribute(
                "venta",
                venta != null
                        ? aVistaTicket(venta)
                        : Map.of()
        );



        List<VentaDetalle> detalles =
                venta != null
                        ? ventaDetalleService.listarPorVenta(id)
                        : List.of();




        List<Map<String,Object>> items =
                detalles.stream()

                .map(d -> {

                    Map<String,Object> m =
                            new LinkedHashMap<>();

                    m.put(
                            "producto",
                            "Orden de preparación N° "
                            + d.getIdOrden()
                    );

                    m.put(
                            "cantidad",
                            d.getCantidad()
                    );


                    m.put(
                            "precio",
                            d.getPrecioUnitario()
                    );


                    return m;

                })

                .toList();




        model.addAttribute(
                "items",
                items
        );



        return "ventas/ticket";

    }








    // CONVERTIR VENTA PARA TABLA
    private Map<String,Object> aVista(Venta v){


        Map<String,Object> m =
                new LinkedHashMap<>();


        m.put(
                "id",
                v.getIdVenta()
        );


        m.put(
                "paciente",
                v.getPaciente() != null
                ?
                v.getPaciente().getNombres()
                + " "
                + v.getPaciente().getApellidos()

                :
                "Sin paciente"
        );



        m.put(
                "total",
                v.getTotal()
        );


        m.put(
                "fecha",
                v.getFechaVenta()
        );


        m.put(
                "estado",
                v.getEstado() != null
                ?
                v.getEstado()
                :
                "SIN ESTADO"
        );


        return m;

    }






    private Map<String,Object> aVistaTicket(Venta v){


        Map<String,Object> m =
                aVista(v);



        m.put(
                "comprobante",

                v.getTipoComprobante()
                +
                " "
                +
                (
                v.getNumComprobante()!=null
                ?
                v.getNumComprobante()
                :
                ""
                )
        );


        return m;

    }


}