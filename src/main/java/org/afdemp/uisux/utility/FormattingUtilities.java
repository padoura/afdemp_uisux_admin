/*
 * Copyright (C) 2017 padoura <padoura@users.noreply.github.com>
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
 */
package org.afdemp.uisux.utility;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

import org.springframework.stereotype.Component;

/**
 *
 * @author padoura <padoura@users.noreply.github.com>
 */
@Component
public final class FormattingUtilities {

    public static String getFormattedCurrency(BigDecimal amount) {
        Currency euro = Currency.getInstance("EUR");
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("el-GR"));
        format.setCurrency(euro);
        return format.format(amount);
    }

    public static String getFormattedCurrency(int i) {
        return getFormattedCurrency(BigDecimal.valueOf(i));
    }

    private FormattingUtilities() {
    }
    
    public static String getFormattedCurrentDate(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        return date.format(formatter);
    }
    
    public static String getFormattedCurrentDateTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return dateTime.format(formatter);
    }
    
    public static String formatTimestamp(Timestamp timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return timestamp.toLocalDateTime().format(formatter);
    }
}
