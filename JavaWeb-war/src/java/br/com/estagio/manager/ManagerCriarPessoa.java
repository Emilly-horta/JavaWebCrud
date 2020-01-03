package br.com.estagio.manager;

//import br.com.estagio.modelo.Mensagens;
import br.com.estagio.modelo.Pessoa;
import br.com.estagio.servico.PessoaServico;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author nayra
 */
@ViewScoped
@ManagedBean
public class ManagerCriarPessoa {

    private Pessoa pessoa;
    @EJB
    PessoaServico pessoaServico;
    private List<Pessoa> listPessoa;

    @PostConstruct
    public void init() {
        Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String idPessoa = map.get("idPessoa");

        if (idPessoa == null) {
            this.pessoa = new Pessoa();
        } else {
            this.pessoa = pessoaServico.buscar(Long.parseLong(idPessoa));
        }

        listPessoa = pessoaServico.listar(pessoa);
    }

    public void deletePessoa(Pessoa p) {
        pessoaServico.deletePessoa(p);
        init();
    }

    public void deletaTodos() {
        pessoaServico.deletaTodos();
        init();
    }

    public void salvar() {
        pessoaServico.salvar(pessoa);
        init();
        listPessoa = pessoaServico.listar(pessoa);
    }

    public void validarCamposVazios() {
       
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

            if (pessoa.getEmail().isEmpty() && pessoa.getSenha().isEmpty()) {
                Mensagens.messagemInfoRedirect(Mensagens.PermissaoNegada, "CadastroView.xhtml");
            } else {
                pessoaServico.salvar(pessoa);
                init();
                Mensagens.messagemInfoRedirect(Mensagens.SuccessFull, "ListarPessoasView.xhtml");
            }
    }

    public void validarAutenticacao() {

        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

            if ("admin".equals(pessoa.getEmail()) && "123".equals(pessoa.getSenha())) {
                context.redirect("CadastroView.xhtml");
            } else {
                context.redirect("AutenticacaoView.xhtml");
            }
        } catch (IOException ex) {
            System.out.println("Erro! Pagina não pode ser acessada!");
        }
    }

    public void listar() {
        pessoaServico.listar(pessoa);
        init();

    }

    public void editar() {
        pessoaServico.editar(pessoa);
        init();

    }

    public void validarCamposVaziosEdicao() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

            if (pessoa.getEmail().isEmpty() && pessoa.getSenha().isEmpty()) {
                context.redirect("editarView.xhtml");
            } else {
                pessoaServico.editar(pessoa);
                context.redirect("ListarPessoasView.xhtml");
            }

        } catch (IOException ex) {
            System.out.println("Erro!");
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaServico getPessoaServico() {
        return pessoaServico;
    }

    public void setPessoaServico(PessoaServico pessoaServico) {
        this.pessoaServico = pessoaServico;
    }

    public List<Pessoa> getListPessoa() {
        return listPessoa;
    }

    public void setListPessoa(List<Pessoa> listPessoa) {
        this.listPessoa = listPessoa;
    }
}
