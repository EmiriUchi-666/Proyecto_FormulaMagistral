// 1. Modo Oscuro Persistente
const theme = localStorage.getItem('theme') || 'light';
document.documentElement.dataset.bsTheme = theme;

document.getElementById('themeToggle')?.addEventListener('click', () => {
    const newTheme = document.documentElement.dataset.bsTheme === 'light' ? 'dark' : 'light';
    document.documentElement.dataset.bsTheme = newTheme;
    localStorage.setItem('theme', newTheme);
});

// 2. HTMX + CSRF
document.body.addEventListener('htmx:configRequest', (event) => {
    const meta = document.querySelector('meta[name="_csrf"]');
    const headerMeta = document.querySelector('meta[name="_csrf_header"]');
    if (meta && headerMeta) {
        event.detail.headers[headerMeta.content] = meta.content;
    }
});

// 3. SweetAlert2 Eliminar
function confirmarEliminar(url, nombre) {
    Swal.fire({
        title: `¿Eliminar ${nombre}?`,
        text: "Esta acción no se puede deshacer",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#dc3545',
        cancelButtonColor: '#6c757d',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = url;
            const csrf = document.querySelector('meta[name="_csrf"]');
            if (csrf) {
                const input = document.createElement('input');
                input.type = 'hidden';
                input.name = '_csrf';
                input.value = csrf.content;
                form.appendChild(input);
            }
            document.body.appendChild(form);
            form.submit();
        }
    });
}

// 4. Atajo Ctrl+K
document.addEventListener('keydown', (e) => {
    if ((e.ctrlKey || e.metaKey) && e.key === 'k') {
        e.preventDefault();
        document.getElementById('globalSearch')?.focus();
    }
});

// 5. Exportar CSV
function exportarCSV(datos, nombreArchivo) {
    if (!datos || datos.length === 0) return;
    const headers = Object.keys(datos[0]);
    const csv = [
        headers.join(','),
        ...datos.map(row => headers.map(h => `"${row[h] ?? ''}"`).join(','))
    ].join('\n');
    const blob = new Blob(["\uFEFF" + csv], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = nombreArchivo + '.csv';
    link.click();
}