package ui;

import service.Service;
import exceptions.DuplicateException;
import exceptions.LackException;
import exceptions.ValidationException;
import java.util.InputMismatchException;
import java.util.Scanner;
import domain.User;
import java.util.List;

public class Console implements Ui{

    public Service service;

    public Console(Service service){
        this.service = service;
    }

    public void add_user() {
        Long id;
        String first_name, second_name;

        System.out.print("id: ");
        Scanner scan = new Scanner(System.in);
        try{
            id = scan.nextLong();
        } catch (InputMismatchException e){
            System.out.println("Id invalid.");
            return;
        }

        scan.nextLine();
        System.out.print("first name: ");
        first_name = scan.nextLine();
        System.out.print("second name: ");
        second_name = scan.nextLine();

        try {
            service.add_user(id, first_name, second_name);
        } catch (IllegalArgumentException | ValidationException | DuplicateException v){
            System.out.println(v.getMessage());
            return;
        }

        System.out.println("\nAdaugat cu succes!\n");
    }

    public void delete_user(){
        System.out.print("id: ");
        Scanner scan = new Scanner(System.in);
        Long id;
        try{
            id = scan.nextLong();
        } catch (InputMismatchException v){
            System.out.println("Id-ul nu este bun.");
            return;
        }

        try{
            this.service.delete_user(id);
        } catch (LackException | ValidationException v){
            System.out.println(v.getMessage());
            return;
        }

        System.out.println("\nUser sters cu succes!\n");
    }

    public void show_users(){
        try {
            List<User> lst_user = this.service.get_all();
            System.out.println();
            for (User u : lst_user) {
                System.out.println(u + "\n");
            }
        }
        catch (LackException v){
            System.out.println(v.getMessage());
        }

    }

    public void make_friends(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Id-ul userului 1: ");
        Long id1;
        try{
            id1 = scan.nextLong();
        }
        catch (InputMismatchException v) {
            System.out.println("\nid-ul nu este valid.\n");
            return;
        }
        System.out.print("Id-ul userului 2: ");
        Long id2;
        try{
            id2 = scan.nextLong();
        }
        catch (InputMismatchException v){
            System.out.println("\nId-ul este invalid!\n");
            return;
        }

        try{
            this.service.make_friends(id1, id2);
        } catch(DuplicateException | LackException v){
            System.out.println(v.getMessage());
            return;
        }

        System.out.println("\nUserul cu id: " + id1 + " s-a imprietenit cu userul cu id: " + id2 + ".\n");
    }

    public void delete_friends(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Id-ul userului 1: ");
        Long id1;
        try{
            id1 = scan.nextLong();
        }
        catch (InputMismatchException v) {
            System.out.println("\nid-ul nu este valid.\n");
            return;
        }
        System.out.print("Id-ul userului 2: ");
        Long id2;
        try{
            id2 = scan.nextLong();
        }
        catch (InputMismatchException v){
            System.out.println("\nId-ul este invalid!\n");
            return;
        }

        try{
            this.service.delete_friends(id1, id2);
        }
        catch (DuplicateException | LackException v){
            System.out.println(v.getMessage());
            return;
        }

        System.out.println("\nPrietenie stearsa cu succes!\n");
    }

    public void friends_list(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Id-ul userului: ");
        Long id;
        try{
            id = scan.nextLong();
        }
        catch (InputMismatchException v) {
            System.out.println("\nid-ul nu este valid.\n");
            return;
        }

        try{
            List<User> friends_list = this.service.friends_list(id);
            System.out.println("\nPrietenii userului cu id " + id + " sunt:");
            for(User friend : friends_list){
                System.out.println(friend.getFirst_name() + " " + friend.getSecond_name());
            }
            System.out.println();
        }
        catch(LackException v){
            System.out.println(v.getMessage());
        }
    }

    public void communities_number(){
        try{
            int nr_communities = service.communities_number();
            System.out.println("\n" + "Numarul de comunitati: " + nr_communities + "\n");
        }
        catch(RuntimeException v){
            System.out.println("\nError!\n");
        }
    }

    private void show_menu(){
        System.out.println("Optiunile dumneavoastra sunt:");
        System.out.println("1 - Adauga user nou.");
        System.out.println("2 - Sterge userul cu id-ul dat.");
        System.out.println("3 - Afiseaza userii existenti.");
        System.out.println("4 - Adauga o relatie de prietenie intre 2 useri.");
        System.out.println("5 - Sterge o relatie de prietenie.");
        System.out.println("6 - Afiseaza toate relatiile de prietenie.");
        System.out.println("7 - Afiseaza nr de comunitati.");
        System.out.println("8 - Comunitatea cea mai sociabila.");
        System.out.println("0 - Exit.");
        System.out.print("Comanda >> ");
    }

    public void showUi(){
        String command;
        Scanner read_opt = new Scanner(System.in);

        while(true){
            show_menu();

            command = read_opt.nextLine();

            try{
                if(command.equals("1"))
                    add_user();

                else if(command.equals("2"))
                    delete_user();

                else if(command.equals("3"))
                    show_users();

                else if(command.equals("4"))
                    make_friends();

                else if(command.equals("5"))
                    delete_friends();

                else if(command.equals("6"))
                    friends_list();

                else if(command.equals("7"))
                    communities_number();

                else if(command.equals("0"))
                    break;

                else{
                    System.out.println("Comanda nu este buna!");
                }
            }
            catch (ValidationException e){
                System.out.println(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
