package sistemadetestes.test;

import org.junit.Assert;
import org.junit.Test;
import sistemadetestes.pageObject.ProdutoPO;

import static sistemadetestes.test.BaseTest.driver;

public class ProdutoTest extends BaseTest {

    @Test
    public void TC001_deveAbrirModalDeCadastroAoClicarEmCriar() {
        ProdutoPO produtoPO = new ProdutoPO(driver);

        // Garantindo que o botão esteja visível antes de clicar
        produtoPO.esperarElementoVisivel(produtoPO.btnAdicionar, 10);
        produtoPO.clicarAdicionar();

        // Validação de que o modal está aberto
        Assert.assertTrue("Modal não foi aberto ao clicar em Criar.", produtoPO.modalAberto());
    }

    @Test
    public void TC002_deveExibirErroAoTentarSalvarComCamposVazios() {
        ProdutoPO produtoPO = new ProdutoPO(driver);

        produtoPO.esperarElementoVisivel(produtoPO.btnAdicionar, 10);
        produtoPO.clicarAdicionar();

        produtoPO.esperarElementoVisivel(produtoPO.btnSalvar, 10);
        produtoPO.clicarSalvar();

        // Verificando se a mensagem de erro foi exibida
        Assert.assertTrue("Mensagem de erro não foi exibida.", produtoPO.alertaErroVisivel());
    }

    @Test
    public void TC003_deveCadastrarProdutoComDadosValidos() {
        ProdutoPO produtoPO = new ProdutoPO(driver);

        produtoPO.esperarElementoVisivel(produtoPO.btnAdicionar, 10);
        produtoPO.clicarAdicionar();

        produtoPO.preencherFormulario("001", "Produto A", "10", "50.00", "2024-11-29");

        produtoPO.esperarElementoVisivel(produtoPO.btnSalvar, 10);
        produtoPO.clicarSalvar();

        // Verificando se o modal foi fechado após salvar
        Assert.assertTrue("Modal não foi fechado após salvar produto válido.", produtoPO.modalFechado());
    }

    @Test
    public void TC004_deveFecharModalAoClicarEmSairSemSalvar() {
        ProdutoPO produtoPO = new ProdutoPO(driver);

        produtoPO.esperarElementoVisivel(produtoPO.btnAdicionar, 10);
        produtoPO.clicarAdicionar();

        produtoPO.esperarElementoVisivel(produtoPO.btnSair, 10);
        produtoPO.clicarSair();

        // Verificando se o modal foi fechado ao clicar em Sair
        Assert.assertTrue("O modal de cadastro ainda está aberto.", produtoPO.modalFechado());
    }

    @Test
    public void TC005_deveExibirErroAoCadastrarProdutoComCodigoDuplicado() {
        ProdutoPO produtoPO = new ProdutoPO(driver);

        // Primeiro cadastro
        produtoPO.esperarElementoVisivel(produtoPO.btnAdicionar, 10);
        produtoPO.clicarAdicionar();

        produtoPO.preencherFormulario("002", "Produto B", "5", "20.00", "2024-11-29");

        produtoPO.esperarElementoVisivel(produtoPO.btnSalvar, 10);
        produtoPO.clicarSalvar();

        // Segundo cadastro com código duplicado
        produtoPO.esperarElementoVisivel(produtoPO.btnAdicionar, 10);
        produtoPO.clicarAdicionar();

        produtoPO.preencherFormulario("002", "Produto C", "8", "30.00", "2024-11-29");

        produtoPO.esperarElementoVisivel(produtoPO.btnSalvar, 10);
        produtoPO.clicarSalvar();

        // Verificando se a mensagem de erro foi exibida
        Assert.assertTrue("Mensagem de erro por código duplicado não foi exibida.", produtoPO.alertaErroVisivel());
    }
}
