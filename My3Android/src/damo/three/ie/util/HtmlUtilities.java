/*
 * This file is part of My3 Prepay for Android
 *
 * Copyright © 2013  Damien O'Reilly
 *
 * My3 Prepay for Android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * My3 Prepay for Android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with My3 Prepay for Android.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Report bugs or new features at: https://github.com/DamienOReilly/My3Usage
 * Contact the author at:          damienreilly@gmail.com
 */

package damo.three.ie.util;

import org.apache.http.HttpEntity;
import org.jsoup.nodes.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HtmlUtilities {

    public static String getPageContent(HttpEntity entity) throws IOException {

        InputStream in = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in),
                1024 * 8);
        StringBuilder str = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            str.append(line);
        }
        in.close();
        return str.toString();
    }

    public static void removeComments(Node node) {
        for (int i = 0; i < node.childNodes().size(); ) {
            Node child = node.childNode(i);
            if (child.nodeName().equals("#comment"))
                child.remove();
            else {
                removeComments(child);
                i++;
            }
        }
    }

}
