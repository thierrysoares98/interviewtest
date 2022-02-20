# language: pt
# encoding: UTF-8
@PesquisarArtigos
Funcionalidade: Pesquisar artigos dentro do blog

	Cenário: Pesquisar artigo existente utilizando a Lupa na página inicial
		Dado que o usuário esteja na página inicial do AGI
		Quando efetuar uma pesquisa de artigos existentes no blog utilizando a lupa
		Então deve ser exibido uma lista de artigos.

	Cenário: Pesquisar artigos não existente utilizando a Lupa na página inicial
		Dado que o usuário esteja na página inicial do AGI
		Quando efetuar uma pesquisa de artigo não existente no blog utilizando a lupa
		Então deve ser exibido na label de resultado "Nenhum resultado"