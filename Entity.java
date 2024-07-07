package domain;

import java.io.Serializable;

public class Entity <ID> implements Serializable{

    private static final long serialVersionUID = 7331115341259248461L;
    private ID id;

    public ID get_id(){
        return this.id;
    }

    public void set_id(ID new_id){
        this.id = new_id;
    }
}
