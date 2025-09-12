# Composite Design Pattern

This project demonstrates the **Composite Design Pattern** using a file system hierarchy as an example.

## What is the Composite Pattern?

The Composite pattern is a structural design pattern that allows you to compose objects into tree structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions of objects uniformly.

## Key Components

### 1. Component Interface (`FileSystemComponent`)
- Defines the common interface for all objects in the composition
- Both leaf and composite objects implement this interface
- Methods: `getName()`, `getSize()`, `getFileCount()`, `display()`, `add()`, `remove()`

### 2. Leaf Class (`File`)
- Represents leaf objects in the composition
- Has no children
- Implements all component methods (add/remove are no-ops for files)

### 3. Composite Class (`Directory`)
- Defines behavior for components having children
- Stores child components
- Delegates operations to its children

## Benefits

1. **Uniform Treatment**: Clients can treat individual objects and compositions uniformly
2. **Simplified Client Code**: No need to distinguish between leaf and composite objects
3. **Easy to Add New Types**: New component types can be added without changing existing code
4. **Recursive Structure**: Natural representation of tree structures

## How to Run

```bash
# Compile the Java code
javac -d bin src/App.java

# Run the example
java -cp bin App
```

## Example Output

The program creates a file system structure and demonstrates:

1. **Tree Structure Display**: Shows the hierarchical file system with proper indentation
2. **Aggregate Operations**: Calculates total size and file count recursively
3. **Uniform Treatment**: Treats files and directories as the same type of object

## Real-World Applications

- **File Systems**: Directories and files
- **GUI Components**: Windows, panels, buttons
- **Organization Charts**: Employees, departments, companies
- **Menu Systems**: Menu items and submenus
- **Document Structures**: Sections, paragraphs, sentences

## Pattern Structure

```
Component (FileSystemComponent)
├── Leaf (File)
└── Composite (Directory)
    ├── Component (File)
    ├── Component (Directory)
    └── Component (File)
```

This pattern is particularly useful when you need to represent a hierarchy of objects and want to treat both individual objects and compositions uniformly.
