package repository;

import domain.Entity;
import java.util.List;

public interface Repo<ID, E extends Entity<ID>>{
    /**
     * Cauta entitatea cu id-ul dat
     */
    E find_entity(ID id);

    /**
     * Returneaza toate entitatile
     */
    List<E> get_all();

    /**
     * Adauga entitatea in repo
     */
    E add_entity(E new_entity);

    /**
     * Sterge entitatea cu id-ul dat
     */
    ID delete_entity(ID id);

    /**
     * modifica entitatea daca exista
     */
    E modif_entity(E modif_entity);

    /**
     * Creeaza o prietenie noua intre 2 utilizatori
     */
    void make_friends(E e1, E e2);

    /**
     * Sterge o prietenie dintre 2 utilizatori
     */
    void delete_friends(E e1, E e2);
}
