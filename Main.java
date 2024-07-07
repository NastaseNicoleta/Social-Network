import repository.InMemoryRepo;
import service.Service;
import validators.UserValidator;
import ui.Console;
import domain.User;

public class Main {
    public static void main(String[] args) {

        UserValidator user_validator = new UserValidator();
        InMemoryRepo<Long, User> repo = new InMemoryRepo(user_validator);
        Service service = new Service(repo);
        Console ui = new Console(service);

        ui.showUi();
    }
}