package stars.controller;

import stars.entity.*;

/**
 * Handles the set vacancy limit of the Admin Controller
 */
public class SetVacancyLimitController {
    /**
     * Set the vacancy limit to an index Can increase the vacancy limit of the
     * index, which will dequeue waitlisted students and confirm their positions and
     * send notifications Can decrease the vacancy limit as long the number of
     * confirmed students is still within the limit Cannot decrease the vacancy
     * limit if the new vacancy limit is less than the number of confirmed students
     * in the index
     * 
     * @param selectedIndex   Index whose vacancy will be changed
     * @param newVacancyLimit new vacancy limit
     * @return true if successfully changed, false if new vacancy limit is less the
     *         the number of confirmed students in the index
     */
    public boolean setVacancyLimit(Index selectedIndex, int newVacancyLimit) {
        // if the user tries to increase the vacancy limit
        if (newVacancyLimit > selectedIndex.getVacancyLimit()) {
            int count = 0;
            int oldVacancy = selectedIndex.getVacancy();
            // if the confirmedlist is now full
            if (selectedIndex.getVacancy() == 0) {
                // while there are still students in the waitlist
                while (selectedIndex.getWaitList().size() > 0) {
                    Student student = selectedIndex.dequeueStudent();
                    selectedIndex.addStudentToConfirmedList(student);
                    student.sendNotification(selectedIndex);
                    count++;
                }
                // if there are no more students in the waitlist and there are still
                // some empty vacancies, set the vacancy as the leftover slots
                selectedIndex.setVacancy(newVacancyLimit - oldVacancy - count);
                selectedIndex.setVacancyLimit(newVacancyLimit);
                return true;
            } else {
                // if the confirmed list is not full, simply increase the vacancy
                selectedIndex
                        .setVacancy(newVacancyLimit - (selectedIndex.getVacancyLimit() - selectedIndex.getVacancy()));
                selectedIndex.setVacancyLimit(newVacancyLimit);
                return true;
            }
            // if the user is trying to decrease the vacancy limit
        } else if (newVacancyLimit < selectedIndex.getVacancyLimit()) {
            // check if the number of students already in the list exceeds the
            // the newVacancyLimit, if so, this will not be allowed
            if ((selectedIndex.getVacancyLimit() - selectedIndex.getVacancy()) > newVacancyLimit) {
                return false;
            } else {
                // otherwise, set the new vacancy to be the new limit - the number of students
                // currently inside
                selectedIndex
                        .setVacancy(newVacancyLimit - (selectedIndex.getVacancyLimit() - selectedIndex.getVacancy()));
                selectedIndex.setVacancyLimit(newVacancyLimit);
                return true;
            }
        }
        // if the newVacancy is the same as the current vacancy
        return true;
    }
}
