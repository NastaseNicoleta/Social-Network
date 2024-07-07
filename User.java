package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
    Clasa pentru a tine informatiile utilizatorilor
 */

public class User extends Entity<Long>{
    private String first_name;
    private String second_name;
    private List<User> friends;

    public User(Long id, String first_name, String second_name){
        this.set_id(id);
        this.first_name = first_name;
        this.second_name = second_name;
        this.friends = new ArrayList<>();
    }

    //Functii de getter
    public String getFirst_name(){
        return this.first_name;
    }

    public String getSecond_name(){
        return this.second_name;
    }

    public List<User> getFriends(){
        return this.friends;
    }

    //Functii de setter
    public void setFirst_name(String new_first_name){
        this.first_name = new_first_name;
    }

    public void setSecond_name(String new_second_name) {
        this.second_name = new_second_name;
    }

    //Adaugare si stergere utilizator
    public void add_friend(User user){
        this.friends.add(user);
    }
    public void delete_friend(User user){
        this.friends.remove(user);
    }

    public String toString(){
        StringBuilder friendsStr = new StringBuilder();

        friendsStr.append("[");
        int ok = 1;
        for (User u : this.friends) {
            if(ok == 1){
                ok = 0;
            }
            else friendsStr.append(" | ");
            friendsStr.append(u.get_id()).append(" ").append(u.getFirst_name()).append(" ").append(u.getSecond_name());
        }
        friendsStr.append("]");

        return "User: " + "id - " + get_id() + " | " + " first name - " + getFirst_name() + " | " + " second name - " + getSecond_name() +
                "\n" + "friends - " + friendsStr + ".";
    }

    //definirea cand 2 users sunt egali
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return first_name.equals(user.first_name) && second_name.equals(user.second_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, second_name);
    }
}
