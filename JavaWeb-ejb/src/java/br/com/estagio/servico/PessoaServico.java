/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estagio.servico;

import br.com.estagio.modelo.Pessoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nayra
 */
@Stateless
public class PessoaServico {

    @PersistenceContext
    private EntityManager entityManager;

   /* public Pessoa buscarPessoa(String email, String senha) {
        Query query = entityManager.createNativeQuery("select * from Pessoa where email=?1 and senha=?2");
        query.setParameter("1", email);
        query.setParameter("2", senha);
        Pessoa pessoa = (Pessoa) query.getSingleResult();
        return pessoa;
    } */

    public void salvar(Pessoa p) {
        entityManager.persist(p);

    }
    /*
    public Pessoa validarCampoCadastro(Pessoa p){
        entityManager.persist(p);
        return p;
    }
    * **/

    public List<Pessoa> listar(Pessoa p) {
        Query list = entityManager.createQuery("select p from Pessoa p");
        return list.getResultList();
    }

    public void deletePessoa(Pessoa p) {
        if (!entityManager.contains(p)) {
            p = entityManager.merge(p);
        }
        entityManager.remove(p);
    }

    public Pessoa editar(Pessoa p) {
        entityManager.merge(p);
        return p;
    }

    public void deletaTodos() {
        Query query = entityManager.createNativeQuery("delete from Pessoa");
        query.executeUpdate();
    }
    public Pessoa buscar(Long id){
        Pessoa pessoa = entityManager.find(Pessoa.class, id);
        return pessoa;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}