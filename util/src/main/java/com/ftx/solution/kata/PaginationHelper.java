package com.ftx.solution.kata;

import java.util.List;

/**
 * For this exercise you will be strengthening your page-fu mastery. You will complete the PaginationHelper class, which is a utility class helpful for querying paging information related to an array.
 * <p>
 * The class is designed to take in an array of values and an integer indicating how many items will be allowed per each page. The types of values contained within the collection/array are not relevant.
 * <p>
 * The following are some examples of how this class is used:
 * <p>
 * PaginationHelper<Character> helper = new PaginationHelper(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), 4);
 * helper.pageCount(); //should == 2
 * helper.itemCount(); //should == 6
 * helper.pageItemCount(0); //should == 4
 * helper.pageItemCount(1); // last page - should == 2
 * helper.pageItemCount(2); // should == -1 since the page is invalid
 * <p>
 * // pageIndex takes an item index and returns the page that it belongs on
 * helper.pageIndex(5); //should == 1 (zero based index)
 * helper.pageIndex(2); //should == 0
 * helper.pageIndex(20); //should == -1
 * helper.pageIndex(-10); //should == -1
 *
 * @author puan
 * @date 2019-04-11 10:59
 **/
public class PaginationHelper<I> {

    private List<I> list;

    private int rows;

    private int pages = 0;

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
        list = collection;
        rows = itemsPerPage;
        pages = list.size() / rows + 1;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return list.size();
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return pages;
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        if (pageIndex >= 0 && pageIndex < pages - 1) {
            return rows;
        } else if (pageIndex == pages - 1) {
            return list.size() - pageIndex * rows;
        }
        return -1;
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        if (itemIndex > list.size() - 1 || itemIndex < 0) {
            return -1;
        }
        return itemIndex / rows;
    }
}
