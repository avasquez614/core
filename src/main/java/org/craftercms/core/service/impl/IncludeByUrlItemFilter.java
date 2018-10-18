/*
 * Copyright (C) 2007-2018 Crafter Software Corporation. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.craftercms.core.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.craftercms.commons.lang.RegexUtils;
import org.craftercms.core.service.Item;
import org.craftercms.core.service.ItemFilter;

import java.util.Arrays;
import java.util.List;

/**
 * {@link ItemFilter} that rejects an item if its URL doesn't match any one of a list of regexes.
 *
 * @author avasquez
 */
public class IncludeByUrlItemFilter implements ItemFilter {

    private String[] includeRegexes;

    public IncludeByUrlItemFilter(String[] includeRegexes) {
        this.includeRegexes = includeRegexes;
    }

    @Override
    public boolean runBeforeProcessing() {
        return true;
    }

    @Override
    public boolean runAfterProcessing() {
        return false;
    }

    @Override
    public boolean accepts(Item item, List<Item> acceptedItems, List<Item> rejectedItems,
                           boolean runningBeforeProcessing) {
        return ArrayUtils.isEmpty(includeRegexes) || RegexUtils.matchesAny(item.getUrl(), includeRegexes);
    }

    @Override
    public String toString() {
        return "IncludeByUrlItemFilter{" +
               "includeRegexes=" + Arrays.toString(includeRegexes) +
               '}';
    }

}
