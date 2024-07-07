package repository;

import domain.Entity;
import domain.User;
import exceptions.LackException;
import validators.Validator;
import exceptions.ValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepo<ID, E extends Entity<ID>> implements Repo<ID, E> {
    protected Validator<E> validator;
    protected Map<ID, E> entities;

    public InMemoryRepo(Validator<E> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }

    /**
     * Cauta entitatea cu id-ul dat
     */
    public E find_entity(ID id){
        if(id == null){
            throw new IllegalArgumentException("\nId-ul nu este valid!\n");
        }
        else if(entities.get(id) == null){
            throw new LackException("\nId-ul nu este valid!\n");
        }
        else{
            return entities.get(id);
        }
    }

    /**
     * Returneaza toate entitatile
     */
    public List<E> get_all(){
        return entities.values().stream().toList();
    }

    /**
     * Adauga entitatea in repo
     */
    public E add_entity(E new_entity){
        if(entities == null)
            throw new IllegalArgumentException("Entitatea nu poate sa fie NULL.");
        if(entities.get(new_entity.get_id()) != null)
            throw new IllegalArgumentException("Id deja folosit.");
        else {
            this.validator.validate(new_entity);
            entities.put(new_entity.get_id(), new_entity);
        }
        return null;
    }

    /**
     * Sterge entitatea cu id-ul dat
     */
    public ID delete_entity(ID id){
        if(entities.containsKey(id)){
            delete_frineds_user(id);
            entities.remove(id);
            return id;
        }
        else{
            throw new ValidationException("Utilizatorul nu exista.");
        }
    }

    private void delete_frineds_user(ID id){
        E entity = this.entities.get(id);
        User user = (User) entity;

        List<User> lst_friends = user.getFriends();

        for(User friend : lst_friends){
            friend.delete_friend(user);
        }

    }

    /**
     * modifica entitatea daca exista
     */
    public E modif_entity(E modif_entity){
        if(entities.get(modif_entity.get_id())!= null) {
            entities.put(modif_entity.get_id(), modif_entity);
            return modif_entity;
        }
        return null;
    }

    /**
     * Adauga prietenie noua
     */
    public void make_friends(E e1, E e2){
        User user1 = (User) e1;
        User user2 = (User) e2;

        user1.add_friend(user2);
        user2.add_friend(user1);
    }

    public void delete_friends(E e1, E e2){
        User user1 = (User) e1;
        User user2 = (User) e2;

        user1.delete_friend(user2);
        user2.delete_friend(user1);
    }

}
