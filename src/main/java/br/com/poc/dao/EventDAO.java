package br.com.poc.dao;

import br.com.poc.entity.Event;
import br.com.poc.exception.GenericPersistenciaException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Qualifier("eventDAO")
public class EventDAO extends PersistenciaDao<Event> {

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(EventDAO.class);


    @Transactional(propagation = Propagation.REQUIRED)
    public void saveEvent(Event Event) throws GenericPersistenciaException {

        if(Event == null) {
            throw new GenericPersistenciaException("Evento deve ser preenchido");
        }

        save(Event);
        getEntityManager().flush();

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateEvent(Event Event) throws GenericPersistenciaException {

        if(Event == null) {
            throw new GenericPersistenciaException("Evento deve ser preenchido");
        }


        update(Event);
        getEntityManager().flush();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeEvent(Integer idEvent) throws GenericPersistenciaException {



        if(idEvent == null) {
            throw new GenericPersistenciaException("O Id de Evento deve ser informado");
        }

        delete(idEvent);
        getEntityManager().flush();
    }

    public List<Event> list() throws GenericPersistenciaException {

        try {

            StringBuilder hql = new StringBuilder().append("SELECT e FROM Event e ");
            hql.append(" WHERE 1 = 1 ");

            //hql.append(" ORDER BY e");
            Query query = getEntityManager().createQuery(hql.toString());

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    public Event findEventById(Integer idEvent) throws GenericPersistenciaException {

        try {

            return findById(idEvent);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }

    }

}
