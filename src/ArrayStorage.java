import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int tempResume = 0;


    void clear() {
        Arrays. fill(storage, 0 , tempResume, null);
        tempResume = 0;
    }

    void save(Resume r) {
        if ( tempResume < storage.length) {
            storage[tempResume] = r;
            tempResume++;
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
            if (uuid.equals(storage[i].uuid)) {
                storage[i] = storage[tempResume - 1];
                tempResume--;
                break;
            }else {
                continue;
            }

        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, tempResume);

    }

    int size() {
        return tempResume;
    }

}

