/*
 * Copyright (c) 1999, 2023, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.sun.security.auth;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;

import java.security.Principal;

/**
 * This class implements the {@code Principal} interface
 * and represents a Windows NT user.
 *
 * <p> Principals such as this {@code NTUserPrincipal}
 * may be associated with a particular {@code Subject}
 * to augment that {@code Subject} with an additional
 * identity.  Refer to the {@code Subject} class for more information
 * on how to achieve this.  Authorization decisions can then be based upon
 * the Principals associated with a {@code Subject}.
 *
 * @see java.security.Principal
 * @see javax.security.auth.Subject
 */
public class NTUserPrincipal implements Principal, java.io.Serializable {

    @java.io.Serial
    private static final long serialVersionUID = -8737649811939033735L;

    /**
     * @serial
     */
    private String name;

    /**
     * Create an {@code NTUserPrincipal} with a Windows NT username.
     *
     * @param name the Windows NT username for this user.
     *
     * @exception NullPointerException if the {@code name}
     *            is {@code null}.
     */
    public NTUserPrincipal(String name) {
        if (name == null) {
            java.text.MessageFormat form = new java.text.MessageFormat
                (sun.security.util.ResourcesMgr.getAuthResourceString
                        ("invalid.null.input.value"));
            Object[] source = {"name"};
            throw new NullPointerException(form.format(source));
        }
        this.name = name;
    }

    /**
     * Return the Windows NT username for this {@code NTPrincipal}.
     *
     * @return the Windows NT username for this {@code NTPrincipal}
     */
    public String getName() {
        return name;
    }

    /**
     * Return a string representation of this {@code NTPrincipal}.
     *
     * @return a string representation of this {@code NTPrincipal}.
     */
    public String toString() {
        java.text.MessageFormat form = new java.text.MessageFormat
                (sun.security.util.ResourcesMgr.getAuthResourceString
                        ("NTUserPrincipal.name"));
        Object[] source = {name};
        return form.format(source);
    }

    /**
     * Compares the specified Object with this {@code NTUserPrincipal}
     * for equality.  Returns true if the given object is also a
     * {@code NTUserPrincipal} and the two NTUserPrincipals
     * have the same name.
     *
     * @param o Object to be compared for equality with this
     *          {@code NTPrincipal}.
     *
     * @return true if the specified Object is equal to this
     *          {@code NTPrincipal}.
     */
    @Pure
    @EnsuresNonNullIf(expression="#1", result=true)
    public boolean equals(@Nullable Object o) {
            if (o == null)
                return false;

        if (this == o)
            return true;

        if (!(o instanceof NTUserPrincipal))
            return false;
        NTUserPrincipal that = (NTUserPrincipal)o;

        return name.equals(that.getName());
    }

    /**
     * Return a hash code for this {@code NTUserPrincipal}.
     *
     * @return a hash code for this {@code NTUserPrincipal}.
     */
    public int hashCode() {
            return this.getName().hashCode();
    }


    /**
     * Restores the state of this object from the stream.
     *
     * @param  stream the {@code ObjectInputStream} from which data is read
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if a serialized class cannot be loaded
     */
    @java.io.Serial
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        if (name == null) {
            java.text.MessageFormat form = new java.text.MessageFormat
                    (sun.security.util.ResourcesMgr.getAuthResourceString
                            ("invalid.null.input.value"));
            Object[] source = {"name"};
            throw new InvalidObjectException(form.format(source));
        }
    }
}
