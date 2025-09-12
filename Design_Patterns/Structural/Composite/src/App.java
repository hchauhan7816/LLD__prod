import java.util.ArrayList;
import java.util.List;

/**
 * Composite Design Pattern Implementation
 * 
 * The Composite pattern allows you to compose objects into tree structures
 * to represent part-whole hierarchies. It lets clients treat individual objects
 * and compositions of objects uniformly.
 * 
 * This example demonstrates a file system where both files and directories
 * can be treated as the same type of object.
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Composite Design Pattern Demo ===\n");
        
        // Create a file system structure
        FileSystemComponent root = new Directory("Root");
        
        // Create some files
        FileSystemComponent file1 = new File("document.txt", 1024);
        FileSystemComponent file2 = new File("image.jpg", 2048);
        FileSystemComponent file3 = new File("readme.md", 512);
        
        // Create a subdirectory
        FileSystemComponent documents = new Directory("Documents");
        FileSystemComponent file4 = new File("report.pdf", 3072);
        FileSystemComponent file5 = new File("presentation.pptx", 4096);
        
        // Create another subdirectory
        FileSystemComponent pictures = new Directory("Pictures");
        FileSystemComponent file6 = new File("vacation.jpg", 5120);
        FileSystemComponent file7 = new File("family.png", 2560);
        
        // Build the hierarchy
        documents.add(file4);
        documents.add(file5);
        
        pictures.add(file6);
        pictures.add(file7);
        
        root.add(file1);
        root.add(file2);
        root.add(file3);
        root.add(documents);
        root.add(pictures);
        
        // Demonstrate the composite pattern
        System.out.println("File System Structure:");
        System.out.println("======================");
        root.display(0);
        
        System.out.println("\nTotal Size: " + root.getSize() + " bytes");
        System.out.println("Total Files: " + root.getFileCount() + " files");
        
        // Demonstrate treating individual and composite objects uniformly
        System.out.println("\n=== Uniform Treatment Demo ===");
        List<FileSystemComponent> components = new ArrayList<>();
        components.add(file1);           // Individual file
        components.add(documents);       // Directory (composite)
        components.add(file6);           // Individual file
        
        for (FileSystemComponent component : components) {
            System.out.println("Component: " + component.getName());
            System.out.println("  Size: " + component.getSize() + " bytes");
            System.out.println("  Files: " + component.getFileCount() + " files");
            System.out.println();
        }
    }
}

/**
 * Component interface - defines the common interface for all objects
 * in the composition, both leaf and composite objects.
 */
interface FileSystemComponent {
    String getName();
    int getSize();
    int getFileCount();
    void display(int depth);
    void add(FileSystemComponent component);
    void remove(FileSystemComponent component);
}

/**
 * Leaf class - represents leaf objects in the composition.
 * A leaf has no children.
 */
class File implements FileSystemComponent {
    private String name;
    private int size;
    
    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getSize() {
        return size;
    }
    
    @Override
    public int getFileCount() {
        return 1; // A file counts as 1 file
    }
    
    @Override
    public void display(int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "📄 " + name + " (" + size + " bytes)");
    }
    
    @Override
    public void add(FileSystemComponent component) {
        // Files cannot have children - this is a no-op
        System.out.println("Cannot add to a file: " + name);
    }
    
    @Override
    public void remove(FileSystemComponent component) {
        // Files cannot have children - this is a no-op
        System.out.println("Cannot remove from a file: " + name);
    }
}

/**
 * Composite class - defines behavior for components having children
 * and stores child components.
 */
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children;
    
    public Directory(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent child : children) {
            totalSize += child.getSize();
        }
        return totalSize;
    }
    
    @Override
    public int getFileCount() {
        int totalFiles = 0;
        for (FileSystemComponent child : children) {
            totalFiles += child.getFileCount();
        }
        return totalFiles;
    }
    
    @Override
    public void display(int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "📁 " + name + "/");
        
        for (FileSystemComponent child : children) {
            child.display(depth + 1);
        }
    }
    
    @Override
    public void add(FileSystemComponent component) {
        children.add(component);
    }
    
    @Override
    public void remove(FileSystemComponent component) {
        children.remove(component);
    }
}
