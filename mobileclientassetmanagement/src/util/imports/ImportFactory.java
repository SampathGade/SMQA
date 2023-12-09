package mobileclientassetmanagement.src.util.imports;


public class ImportFactory {
    public static ImportInterface getHandler(int type) {
        switch (type) {
            case 1:
                return new UserImportHandler();
        }
    }
}
