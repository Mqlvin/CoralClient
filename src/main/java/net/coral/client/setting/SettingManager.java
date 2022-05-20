package net.coral.client.setting;

import net.coral.client.file.FileReaders;
import net.coral.client.file.FileWriters;
import net.coral.client.module.Module;
import net.coral.client.setting.annotation.BooleanSetting;
import net.coral.client.setting.annotation.IntegerSetting;
import net.coral.client.setting.annotation.StringSetting;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class SettingManager {
    private File location;
    private final Module parent;

    private boolean shouldEnable = false;

    private final HashMap<Field, StringSetting> strings = new HashMap<>();
    private final HashMap<Field, IntegerSetting> integers = new HashMap<>();
    private final HashMap<Field, BooleanSetting> booleans = new HashMap<>();

    public SettingManager(Module module, File location) {
        this.location = location;
        this.parent = module;

        Field[] fields = module.getClass().getDeclaredFields();
        for(Field field : fields) {
            if(field.getAnnotations().length != 0) continue;
            SettingType type = getSettingType(field);
            if(type == SettingType.STRING) {
                StringSetting setting = field.getAnnotation(StringSetting.class);
                if(setting != null && setting.def() != null && setting.description() != null) strings.put(field, setting);
            } else if(type == SettingType.INTEGER) {
                IntegerSetting setting = field.getAnnotation(IntegerSetting.class);
                if(setting != null && setting.description() != null) integers.put(field, setting);
            } else if(type == SettingType.BOOLEAN) {
                BooleanSetting setting = field.getAnnotation(BooleanSetting.class);
                if(setting != null && setting.description() != null) booleans.put(field, setting);
            }
        }

        load();
    }

    public void load() {
        if(!this.location.exists()) {
            updateAllDefaults();
            return;
        }
        ArrayList<String> loadItems = FileReaders.readAsList(this.location);

        for(String item : loadItems) {
            int slashIndex = item.indexOf("/");
            String type = item.substring(0, slashIndex);
            String name = item.substring(slashIndex + 1, item.indexOf("="));

            if(name.equalsIgnoreCase("enabled")) {
                this.shouldEnable = item.substring(item.indexOf("=") + 1).equalsIgnoreCase("true");
            }

            try {
                Field field;
                switch(type) {
                    case("str"): {
                        field = getByName(name, SettingType.STRING);
                        if(field == null) continue;

                        field.setAccessible(true);
                        field.set(this, item.substring(item.indexOf("=") + 1));
                    }
                    case("int"): {
                        field = getByName(name, SettingType.INTEGER);
                        if(field == null) continue;

                        field.setAccessible(true);
                        field.set(this, Integer.parseInt(item.substring(item.indexOf("=") + 1)));
                    }
                    case("bool"): {
                        field = getByName(name, SettingType.BOOLEAN);
                        if(field == null) continue;

                        field.setAccessible(true);
                        field.set(this, item.substring(item.indexOf("=") + 1).equalsIgnoreCase("true"));
                    }
                }
            } catch(IllegalAccessException e) {
                e.printStackTrace();
            }

            updateAllDefaults();
        }
    }

    public void save() {
        ArrayList<String> saveBuilder = new ArrayList<>();
        saveBuilder.add("bool/enabled=" + this.parent.isEnabled());
        try {
            for(Field field : strings.keySet()) {
                saveBuilder.add("str/" + field.getName() + "=" + field.get(String.class));
            }
            for(Field field : integers.keySet()) {
                saveBuilder.add("int/" + field.getName() + "=" + field.get(Integer.class));
            }
            for(Field field : booleans.keySet()) {
                saveBuilder.add("bool/" + field.getName() + "=" + (field.get(Boolean.class).toString().equalsIgnoreCase("true")));
            }
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }


        FileWriters.writeFile(this.location, saveBuilder);
    }

    private SettingType getSettingType(Field field) {
        if(field.getAnnotation(IntegerSetting.class) != null) return SettingType.INTEGER;
        if(field.getAnnotation(BooleanSetting.class) != null) return SettingType.BOOLEAN;
        return SettingType.STRING;
    }

    private Field getByName(String name, SettingType type) {
        if(type.equals(SettingType.STRING)) {
            return this.strings.keySet().stream().filter(m -> m.getName().equals(name)).findAny().orElse(null);
        } else if(type.equals(SettingType.INTEGER)) {
            return this.integers.keySet().stream().filter(m -> m.getName().equals(name)).findAny().orElse(null);
        } else if(type.equals(SettingType.BOOLEAN)) {
            return this.booleans.keySet().stream().filter(m -> m.getName().equals(name)).findAny().orElse(null);
        }
        return null;
    }

    private void updateAllDefaults() {
        try {
            for(Field field : strings.keySet()) {
                if(field.get(String.class) == null) {
                    field.set(this, strings.get(field).def());
                }
            }
            for(Field field : integers.keySet()) {
                if(field.get(Integer.class) == null) {
                    field.set(this, integers.get(field).def());
                }
            }
            for(Field field : booleans.keySet()) {
                if(field.get(Boolean.class) == null) {
                    field.set(this, booleans.get(field).def());
                }
            }
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setLocation(File location) {
        this.location = location;
    }

    public File getLocation() {
        return location;
    }

    public boolean shouldEnable() {
        return shouldEnable;
    }
}
