import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int tempForSave = 0;


    void clear() {
        Arrays.fill(storage, null);

    }

    void save(Resume r) {
        if (storage.length > tempForSave) {
            storage[tempForSave] = r;
            tempForSave++;
        }

    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null) {
                if (uuid.equals(resume.uuid)) {
                    return resume;
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)){
                for (int j = i; j < storage.length - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                storage[storage.length - 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int countLength = 0;
        for (int i = 0; i < storage.length; i++) {
           if (storage[i] != null){
               countLength++;
           }
        }

        return Arrays.copyOf(storage, countLength);
    }

        int size () {
            int sizeTemp = 0;
            for (Resume resume : storage) {
                if (resume != null) {
                    sizeTemp++;
                }
            }
            return sizeTemp;
        }
}

