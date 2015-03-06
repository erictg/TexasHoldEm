package backend;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.UpdateOneModel;
import org.bson.Document;



public class MongoControl {

    public static boolean checkUserName(String nameToCheck, MongoDatabase database){
        MongoCollection<Document> collection = database.getCollection("Users");
        Document doc = collection.find(new Document("name", nameToCheck)).first();
        if(doc != null){
            return true;
        }
        return false;
    }

    public static void addUser(User user, MongoDatabase database){
        MongoCollection<Document> collection = database.getCollection("Users");
        Document doc = new Document("name", user.getName())
                .append("password", user.getPassword())
                .append("totalMoneyGained", 0d)
                .append("totalMoneyLost", 0d)
                .append("money", 0d)
                .append("ID", user.getID());
        collection.insertOne(doc);
        System.out.println("user saved");
    }

    public static User login(String username, String password, MongoDatabase database){
        MongoCollection<Document> collection = database.getCollection("Users");
        Document doc = collection.find(new Document("name", username)).first();
        if(doc != null){
            if(doc.getString("password").equals(password)){
                return documentToUser(doc);
            }else{
                return null;
            }
        }
        return null;
    }


    public static final int MONEY = 0;
    public static final int TOTAL_MONEY_LOST = 1;
    public static final int TOTAL_MONEY_GAINED = 2;

    public static void updateUser(int updateID, int updatePiece, MongoDatabase database, int ID){
        MongoCollection<Document> collection = database.getCollection("Users");
        Document doc = collection.find(new Document("ID", ID)).first();

        if(updateID == MONEY){
            doc.replace("money", updatePiece);
            collection.insertOne(doc);
        }
    }

    public static User documentToUser(Document doc){
        User u = new User();
        u.setID(doc.getInteger("ID"));
        u.setName(doc.getString("name"));
        u.setPassword("password");
        u.setTotalMoneyGained(doc.getDouble("totalMoneyGained"));
        u.setTotalMoneyLost(doc.getDouble("totalMoneyLost"));
        u.setMoney(doc.getDouble("money"));
        return u;
    }

}
