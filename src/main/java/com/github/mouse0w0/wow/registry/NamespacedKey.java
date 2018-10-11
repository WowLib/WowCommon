package com.github.mouse0w0.wow.registry;

import com.google.common.base.Strings;

import javax.annotation.Nonnull;

public class NamespacedKey {

    private String domain;
    private String path;

    public NamespacedKey(String domain, @Nonnull String path) {
        if(Strings.isNullOrEmpty(path))
        	throw new IllegalArgumentException("Path couldn't be null or empty");
        this.domain = Strings.nullToEmpty(domain);
        this.path = path;
    }

    public NamespacedKey(@Nonnull String namespacedKey) {
        if(Strings.isNullOrEmpty(namespacedKey))
        	throw new IllegalArgumentException("NamespacedKey couldn't be null or empty");

        String args[] = namespacedKey.split(":", 2);
        if (args.length < 2) {
            this.domain = "";
            this.path = args[0];
        } else {
            this.domain = args[0];
            this.path = args[1];
        }
    }

    @Nonnull
    public String getDomain() {
        return domain;
    }

    @Nonnull
    public String getPath() {
        return path;
    }

    @Override
    public int hashCode() {
        return domain.hashCode() * 31 + path.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof NamespacedKey))
            return false;

        NamespacedKey other = (NamespacedKey) obj;

        if (!domain.equals(other.domain))
            return false;
        if (!path.equals(other.path))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return Strings.isNullOrEmpty(getDomain()) ? getPath() : getDomain() + ":" + getPath();
    }

}
