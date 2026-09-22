package cominfo;

import controller.PengajuanController;
import view.PengajuanView;

public class Main {
    public static void main(String[] args) {
        PengajuanController controller = new PengajuanController();
        PengajuanView view = new PengajuanView(controller);

        view.jalankan();
    }
}