import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        int i = 0;
        while (i < size && !storage[i].uuid.equals(uuid)) i++;
        return storage[i];
    }

    void delete(String uuid) {
        int i = 0;
        while (i < size && !storage[i].uuid.equals(uuid)) i++;
        System.arraycopy(storage, i + 1, storage, i, size + 1 - i);
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
