package si.lab.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Index {
    @Id
    public ObjectId id;
    public long index;
}
