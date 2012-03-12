/*
 * Copyright (C) 2012 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
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
package org.magmax.master.project.admin.user;

import org.magmax.eswing.crud.CrudTable;
import org.magmax.eswing.crud.DefaultCrudUIDelete;
import org.magmax.master.project.admin.BaseCrudPanel;
import org.magmax.master.project.admin.email.EmailCrudModel;
import org.magmax.master.project.admin.email.EmailCrudTable;
import org.magmax.master.project.admin.email.EmailDialog;
import org.magmax.master.project.admin.phone.PhoneCrudModel;
import org.magmax.master.project.admin.phone.PhoneCrudTable;
import org.magmax.master.project.admin.phone.PhoneDialog;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class UserPanel extends BaseCrudPanel<UserRow> {

    public static final String TITLE_CREATION = "User creation";
    public static final String TITLE_DETAILS = "User Details";
    public static final String TITLE_UPDATE = "Update User";
    private PhoneCrudTable phoneTable;
    private EmailCrudTable emailTable;

    /**
     * Creates new form UserUI
     */
    public UserPanel() {
        initComponents();
        initPhonePanel();
        initEmailPanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nameEntry = new javax.swing.JTextField();
        isAdmin = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        jLabel1.setText("Nombre:");

        isAdmin.setText("Es administrador");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Teléfonos"));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("E-mails"));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(isAdmin)
                        .addGap(0, 217, Short.MAX_VALUE))
                    .addComponent(nameEntry)))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isAdmin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox isAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nameEntry;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitleCreation() {
        return TITLE_CREATION;
    }

    @Override
    public String getTitleUpdate() {
        return TITLE_UPDATE;
    }

    @Override
    public String getTitleDetails() {
        return TITLE_DETAILS;
    }

    @Override
    public UserRow getCrudObject() {
        UserRow result = new UserRow();
        updateCrudObject(result);
        return result;
    }

    @Override
    public void setCrudObject(UserRow item) {
        nameEntry.setText(item.getEntity().getName());
        isAdmin.setSelected(item.getEntity().isAdmin());
        phoneTable.setUser(item.getEntity());
        phoneTable.loadData();
        emailTable.setUser(item.getEntity());
        emailTable.loadData();
    }

    @Override
    public void updateCrudObject(UserRow item) {
        item.getEntity().setName(nameEntry.getText());
        item.getEntity().setAdmin(isAdmin.isSelected());
    }

    @Override
    public void setWidgetsEnabled(boolean enabled) {
        nameEntry.setEnabled(enabled);
        isAdmin.setEnabled(enabled);
    }

    @Override
    public void clearWidgets() {
        nameEntry.setText("");
        isAdmin.setSelected(false);
        phoneTable.setUser(null);
        phoneTable.loadData();
        emailTable.setUser(null);
        emailTable.loadData();
    }

    private void initPhonePanel() {
        phoneTable = new PhoneCrudTable(new PhoneCrudModel());
        jPanel1.add(phoneTable);

        PhoneDialog phoneDialog = new PhoneDialog(null);
        phoneTable.setCrudUICreate(phoneDialog);
        phoneTable.setCrudUIUpdate(phoneDialog);
        phoneTable.setCrudUIDelete(new DefaultCrudUIDelete());
    }

    private void initEmailPanel() {
        emailTable = new EmailCrudTable(new EmailCrudModel());
        jPanel2.add(emailTable);

        EmailDialog emailDialog = new EmailDialog(null);
        emailTable.setCrudUICreate(emailDialog);
        emailTable.setCrudUIUpdate(emailDialog);
        emailTable.setCrudUIDelete(new DefaultCrudUIDelete());
    }
}
