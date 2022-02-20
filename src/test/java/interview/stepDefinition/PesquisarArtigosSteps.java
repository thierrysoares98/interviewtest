package interview.stepDefinition;


import interview.pageObject.PesquisarArtigosPage;
import interview.utils.WebController;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class PesquisarArtigosSteps {

    PesquisarArtigosPage pesquisarArtigosPage = new PesquisarArtigosPage();

    @Before
    public void setup(){
        WebController.getInstance().getDriver_();
    }
    @After
    public void teardown() {
        WebController.quitDriver();
    }
    @Dado("que o usuário esteja na página inicial do AGI")
    public void que_o_usuário_esteja_na_página_inicial_do_AGI() {
        pesquisarArtigosPage.acessarHome("https://blogdoagi.com.br");
    }

    @Quando("efetuar uma pesquisa de artigos existentes no blog utilizando a lupa")
    public void efetuar_uma_pesquisa_de_artigos_existentes_no_blog_utilizando_a_lupa() {
        pesquisarArtigosPage.clicarBtnLupa();
        pesquisarArtigosPage.enviarTextoCampoPesquisar("teste");
        pesquisarArtigosPage.clicarBtnPesquisar();
    }

    @Então("deve ser exibido uma lista de artigos.")
    public void deve_ser_exibido_uma_lista_de_artigos() {
        pesquisarArtigosPage.validarArtigos();
    }

    @Quando("efetuar uma pesquisa de artigo não existente no blog utilizando a lupa")
    public void efetuar_uma_pesquisa_de_artigo_não_existente_no_blog_utilizando_a_lupa() {
        pesquisarArtigosPage.clicarBtnLupa();
        pesquisarArtigosPage.enviarTextoCampoPesquisar("aaaaa123456");
        pesquisarArtigosPage.clicarBtnPesquisar();
    }

    @Então("deve ser exibido na label de resultado {string}")
    public void deve_ser_exibido_na_label_de_resultado(String resultado) {
        pesquisarArtigosPage.validarLabelResultado(resultado);
    }


}
