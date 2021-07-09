import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, size(), null);
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        int i = 0;
        while (null != storage[i] && !storage[i].uuid.equals(uuid)) i++;
        return storage[i];
    }

    void delete(String uuid) {
        int i = 0;
        while (null != storage[i] && !storage[i].uuid.equals(uuid)) i++;
        System.arraycopy(storage, i + 1, storage, i, size() + 1 - i);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int count = 0;
        while (null != storage[count]) count++;
        return count;
    }
}
