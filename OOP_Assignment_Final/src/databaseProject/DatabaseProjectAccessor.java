package databaseProject;

import test.Project;

/**
 * Provides an interface to a database of projects.
 * @author A34_3
 * @version 9 Apr 2023
 */
public class DatabaseProjectAccessor {

    /**
     * Adds a project to the database.
     *
     * @param p The project to be added.
     */
    public static void addProject(Project p) {
        ProjectArray.projectArray.add(p);
        ProjectArray.size++;
    }

    /**
     * Retrieves a project from the database.
     *
     * @param id The ID of the project to be retrieved.
     * @return The project with the specified ID, or null if not found.
     */
    public static Project getProject(int id) {
        try {
            return ProjectArray.projectArray.get(id);
        } catch (Exception e) {
            System.out.println("ProjectID not found");
            return null;
        }
    }

    /**
     * Returns the number of projects in the database.
     *
     * @return The number of projects in the database.
     */
    public static int getSize() {
        return ProjectArray.size;
    }

    /**
     * Updates a project in the database.
     *
     * @param p The project to be updated.
     */
    public static void updateProjectInDatabase(Project p) {
        ProjectArray.projectArray.set(p.getID(), p);
    }


}
