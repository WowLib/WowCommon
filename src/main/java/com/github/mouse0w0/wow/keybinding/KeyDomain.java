package com.github.mouse0w0.wow.keybinding;

public enum KeyDomain {
    UNIVERSAL {
        @Override
        public boolean isConflicts(KeyDomain other) {
            return true;
        }
    },
    GUI {
        @Override
        public boolean isConflicts(KeyDomain other) {
            return other == this || other == UNIVERSAL;
        }
    },
    IN_GAME {
        @Override
        public boolean isConflicts(KeyDomain other) {
            return other == this || other == UNIVERSAL;
        }
    };

    public abstract boolean isConflicts(KeyDomain other);
}
