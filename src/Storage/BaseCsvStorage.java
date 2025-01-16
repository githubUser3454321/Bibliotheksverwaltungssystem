package Storage;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCsvStorage<T> {
    private final String filePath;
    protected List<T> values;
    private List<String> columnNames;
    private List<Class<?>> columnTypes;
    private final Class<T> type; // Add type information
    
    public BaseCsvStorage(String filePath, Class<T> type) {
        this.filePath = filePath;
        this.values = new ArrayList<>();
        this.columnNames = new ArrayList<>();
        this.columnTypes = new ArrayList<>();
        this.type = type; // Store the type
    }

    protected abstract T parseLine(String line);
    protected abstract String formatObjectT(T object);

    public void addColumn(String columnName, Class<?> columnType) {
        columnNames.add(columnName);
        columnTypes.add(columnType);
    }

    public void initializeColumns() {
        loadFromFile();
    }

    public void loadFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    T item = parseLine(line);
                    if (item != null) {
                        values.add(item);
                    }
                }
            } catch (IOException e) {
                System.out.println("Fehler beim Laden der Datei: " + e.getMessage());
            }
        } else {
            System.out.println("CSV-Datei existiert nicht, wird erstellt.");
            createNewFile();
        }
    }

    private void createNewFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create parent directories if they don't exist
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Erstellen der CSV-Datei: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T item : values) {
                writer.write(formatObjectT(item));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Datei: " + e.getMessage());
        }
    }

    public List<T> getAllValues() {
        return new ArrayList<>(values);
    }

    public void addValue(T value) {
        values.add(value);
        saveToFile();
    }


    protected String[] extractValuesFromObject(T object) {
        String[] extractedValues = new String[columnNames.size()];
        for (int i = 0; i < columnNames.size(); i++) {
            try {
                Field field = object.getClass().getDeclaredField(columnNames.get(i));
                field.setAccessible(true);
                Object value = field.get(object);
                extractedValues[i] = (value != null) ? value.toString() : "";
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Fehler beim Extrahieren der Spaltenwerte: " + e.getMessage());
            }
        }
        return extractedValues;
    }

    // Use type to instantiate
    protected T parseLineWithDynamicColumns(String line) {
        try {
            // Create a new instance of T using reflection
            T item = type.getDeclaredConstructor().newInstance();

            // Split the input line into parts
            String[] data = line.split(",");

            // Populate the fields of the item with the parsed data
            for (int i = 0; i < columnNames.size(); i++) {
                String columnName = columnNames.get(i);
                Class<?> columnType = columnTypes.get(i);
                String value = data[i];

                Field field = type.getDeclaredField(columnName);
                field.setAccessible(true);

                if (columnType == String.class) {
                    field.set(item, value);
                } else if (columnType == int.class) {
                    field.set(item, Integer.parseInt(value));
                } else if (columnType == double.class) {
                    field.set(item, Double.parseDouble(value));
                } else {
                    throw new IllegalArgumentException("Unsupported column type: " + columnType);
                }
            }
            return item;
        } catch (Exception e) {
            System.out.println("Fehler beim Erstellen des Objekts aus der CSV-Zeile: " + e.getMessage());
        }
        return null;
    }

    protected String formatObject(T object) {
        String[] values = extractValuesFromObject(object);
        return String.join(",", values);
    }
}
