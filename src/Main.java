import controller.FileController;
import controller.UserController;
import model.FileModel;
import view.FileView;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        FileModel fileModel = new FileModel();
        FileView view = new FileView();
        FileController controller = new FileController(userController, fileModel, view);
        controller.run();
    }
}
