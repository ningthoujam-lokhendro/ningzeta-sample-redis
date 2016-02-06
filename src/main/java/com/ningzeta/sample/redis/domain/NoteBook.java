/**
 *
 */
package com.ningzeta.sample.redis.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Define the Notebook object.
 *
 * @author Ningthoujam Lokhendro
 * @since 27th Jan 2016
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteBook implements Serializable {

    String title;
    String description;
    int numberOfPages;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the numberOfPages
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * @param numberOfPages the numberOfPages to set
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "NoteBook [title=" + title + ", description=" + description + ", numberOfPages=" + numberOfPages + "]";
    }


}
