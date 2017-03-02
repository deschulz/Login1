package net.deschulz.login1;

/**
 * Created by schulz on 3/2/17.
 * The structure of one row in the database
 */

class DBRecord {
    private long id;
    private String name;
    private String password;
    private String hint;

    public void DesDbRecord() {}

    public void setHint(String h) {
        this.hint = h;
    }
    public String getHint() {
        return this.hint;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }

    public void setName(String n) {
        this.name = n;
    }
    public String getName() {
        return this.name;
    }

    void setPassword(String pw) {
        this.password = pw;
    }
    public String getPassword() {
        return this.password;
    }

    public String dump() {
        return String.format("%d, %s, %s", id, name, password);
    }
}
