package validators;

import exceptions.ValidationException;
import domain.User;

public class UserValidator implements Validator<User>{

    public void validate(User user) throws  ValidationException{
        String errors = "";
        if(user.getFirst_name().isEmpty()){
            errors += "First name este empty.\n";
        }
        if(user.getSecond_name().isEmpty()){
            errors += "Second name este empty.\n";
        }
        if(user.get_id() <= 0){
            errors += "Id-ul nu este bun.\n";
        }
        boolean fname = user.getFirst_name().chars().allMatch(Character::isLetter);
        if(!fname){
            errors += "First name trebuie sa contina doar litere.\n";
        }
        boolean sname = user.getFirst_name().chars().allMatch(Character::isLetter);
        if(!sname){
            errors += "Second name trebuie sa contina doar litere.\n";
        }

        if(errors.length()>0)
            throw new ValidationException("\n" + errors);
    }
}
