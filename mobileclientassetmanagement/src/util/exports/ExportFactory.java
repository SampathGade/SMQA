package mobileclientassetmanagement.src.util.exports;

public class ExportFactory {
    public static ExportInterface getHandler(int type) {
        switch (type) {
            case 1:
                return new UserExportHandler();
        }
    }
}
