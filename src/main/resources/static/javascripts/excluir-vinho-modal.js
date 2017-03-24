$('#confirmaExclusaoModal').on('show.bs.modal', function (event) {
	var botao = $(event.relatedTarget);
	var nome = botao.data('id');
	var url = botao.data('url-apagar');
	
	var modal = $(this);
	var form = modal.find('form');
	form.attr('action', url);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o <strong>' + nome + '</strong>?');
});