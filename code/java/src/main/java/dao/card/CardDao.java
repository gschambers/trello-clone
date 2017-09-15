package dao.card;

import datamodel.Card;
import java.util.List;
import java.util.Optional;

public interface CardDao {
    /**
     * @return A list of all cards
     * @throws Exception DataSource exception
     */
    List<Card> getAll() throws Exception;

    /**
     * @param id The card ID
     * @return An optional card by ID, if exists
     * @throws Exception DataSource exception
     */
    Optional<Card> getById(int id) throws Exception;

    /**
     * Adds a new card
     * @param card The card to be added
     * @return True if successful, false otherwise
     * @throws Exception DataSource exception
     */
    boolean add(Card card) throws Exception;

    /**
     * Updates a card
     * @param card The card to be updated
     * @return True if successful, false otherwise
     * @throws Exception DataSource exception
     */
    boolean update(Card card) throws Exception;

    /**
     * Deletes a card
     * @param card The card to be deleted
     * @return True if successful, false otherwise
     * @throws Exception DataSource exception
     */
    boolean delete(Card card) throws Exception;
}
