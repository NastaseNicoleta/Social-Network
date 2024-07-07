package service;

import domain.Entity;
import domain.User;
import exceptions.DuplicateException;
import exceptions.LackException;
import repository.Repo;
import graph.Graph;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Service{

    protected final Repo<Long, User> repo;

    public Service(Repo<Long, User> repo){
        this.repo = repo;
    }

    public void add_user(Long id, String first_name, String second_name) {
        User user = new User(id, first_name, second_name);
        user.set_id(id);
        this.repo.add_entity(user);
    }

    public void delete_user(Long id){
        this.repo.delete_entity(id);
    }

    public List<User> get_all(){

        List<User> lst_users = this.repo.get_all();

        if(lst_users.size() == 0){
            throw new LackException("\nNu exista useri in aplicatie!\n");
        }
        else{
            return lst_users;
        }
    }

    public void make_friends(Long id1, Long id2){
        User user1 = this.repo.find_entity(id1);
        User user2 = this.repo.find_entity(id2);

        if(Objects.equals(user1, user2))
            throw new DuplicateException("\nAi introdus acelasi user!\n");

        if(verif_friends(user1, user2))
            throw new DuplicateException("\nUserii deja sunt prieteni!\n");

        this.repo.make_friends(user1, user2);
    }

    private boolean verif_friends(User u1, User u2){
        List<User> lst_friends = u1.getFriends();

        for(User friend : lst_friends){
            if(friend == u2)
                return true;
        }
        return false;
    }

    public void delete_friends(Long id1, Long id2){
        User user1 = this.repo.find_entity(id1);
        User user2 = this.repo.find_entity(id2);

        if(Objects.equals(user1, user2))
            throw new DuplicateException("\nAti introdus acelasi user!\n");

        if(!verif_friends(user1, user2))
            throw new LackException("\nUserii nu sunt prieteni!\n");

        this.repo.delete_friends(user1, user2);
    }

    public List<User> friends_list(Long id){
        User user = this.repo.find_entity(id);
        List<User> friends_list = user.getFriends();

        if(friends_list.size() == 0){
            throw new LackException("\nUserul nu are prieteni!\n");
        }
        else{
            return friends_list;
        }
    }

    public int communities_number(){
        List<User> lst_users = this.repo.get_all();
        int nr_users = 0;
        int max_id = 0;

        for(User u : lst_users){
            if(u.get_id().intValue() > max_id)
                max_id = u.get_id().intValue();
            nr_users++;
        }

        Graph g = new Graph(max_id);

        for(User user : lst_users) {
            List<User> lst_friends = user.getFriends();

            for (User friend : lst_friends) {
                g.addEdge(((Long) user.get_id()).intValue()-1, ((Long) friend.get_id()).intValue()-1);
            }
        }

        g.DFS();

        if(nr_users < max_id) {
            int nr = max_id - nr_users;
            return g.ConnecetedComponents() - nr;
        }
        else return g.ConnecetedComponents();
    }
}

