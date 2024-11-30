package sistemadetestes.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProdutoPO {

    private WebDriver driver;

    @FindBy(id = "btn-adicionar")
    public WebElement btnAdicionar;

    @FindBy(id = "btn-salvar")
    public WebElement btnSalvar;

    @FindBy(id = "btn-sair")
    public WebElement btnSair;

    @FindBy(id = "codigo")
    public WebElement inputCodigo;

    @FindBy(id = "nome")
    public WebElement inputNome;

    @FindBy(id = "quantidade")
    public WebElement inputQuantidade;

    @FindBy(id = "valor")
    public WebElement inputValor;

    @FindBy(id = "data")
    public WebElement inputData;

    @FindBy(css = ".alert-danger")
    public WebElement alertaErro;

    public ProdutoPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Método para esperar até que um elemento esteja visível
    public void esperarElementoVisivel(WebElement elemento, long timeoutInSeconds) {
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.visibilityOf(elemento));
    }

    // Método para preencher o formulário com validações
    public void preencherFormulario(String codigo, String nome, String quantidade, String valor, String data) {
        if (codigo != null) {
            inputCodigo.clear();
            inputCodigo.sendKeys(codigo);
        }

        if (nome != null) {
            inputNome.clear();
            inputNome.sendKeys(nome);
        }

        if (quantidade != null) {
            inputQuantidade.clear();
            inputQuantidade.sendKeys(quantidade);
        }

        if (valor != null) {
            inputValor.clear();
            inputValor.sendKeys(valor);
        }

        if (data != null) {
            inputData.clear();
            inputData.sendKeys(data);
        }
    }

    // Método para verificar se o modal está aberto
    public boolean modalAberto() {
        return btnSalvar.isDisplayed() && btnSair.isDisplayed();
    }

    // Método para verificar se o modal está fechado
    public boolean modalFechado() {
        return !modalAberto();
    }

    // Método para verificar se o alerta de erro está visível
    public boolean alertaErroVisivel() {
        return alertaErro.isDisplayed();
    }

    // Ações comuns com logs para facilitar a depuração
    public void clicarAdicionar() {
        System.out.println("Clicando no botão 'Adicionar'");
        btnAdicionar.click();
    }

    public void clicarSalvar() {
        System.out.println("Clicando no botão 'Salvar'");
        btnSalvar.click();
    }

    public void clicarSair() {
        System.out.println("Clicando no botão 'Sair'");
        btnSair.click();
    }
}
