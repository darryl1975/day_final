package sg.edu.nus.iss.day38_backend.model;

public class FileInfo {
 
    String filename;

    String fileURL;
    
    public FileInfo(String filename, String fileURL) {
        this.filename = filename;
        this.fileURL = fileURL;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    
}
