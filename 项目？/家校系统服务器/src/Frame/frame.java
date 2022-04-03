package Frame;

import Server.SchoolHomeServer;

import java.io.IOException;
import java.sql.SQLException;
@SuppressWarnings({"all"})
public class frame {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        new SchoolHomeServer();
    }
}
