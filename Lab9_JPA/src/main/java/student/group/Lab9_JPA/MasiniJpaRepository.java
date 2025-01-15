package student.group.Lab9_JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class MasiniJpaRepository {

    @PersistenceContext
    EntityManager em;

    public List<Masina> findAll(){
        TypedQuery<Masina> query = em.createQuery("from Masina", Masina.class);
        return query.getResultList();
    }

    public Masina findById(String id){
        return em.find(Masina.class, id);
    }

    public void deleteById(String id){
        Masina masina=findById(id);
        em.remove(masina);
    }

    public Masina insert(Masina masina){
       return em.merge(masina);
   }


    public Masina update(Masina masina){
        return em.merge(masina);
    }



}
