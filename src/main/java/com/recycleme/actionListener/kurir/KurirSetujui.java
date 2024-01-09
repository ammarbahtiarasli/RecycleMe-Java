package com.recycleme.actionListener.kurir;

import com.recycleme.dao.KurirDao;
import com.recycleme.frame.kurir.KurirFrame;
import com.recycleme.model.kurir.Kurir;

import javax.swing.*;
import java.awt.event.*;

public class KurirSetujui implements ActionListener {
    private KurirFrame kurirFrame;
    private KurirDao kurirDao;
    private Kurir kurir;

    public KurirSetujui(KurirFrame kurirFrame, KurirDao kurirDao) {
        this.kurirFrame = kurirFrame;
        this.kurirDao = kurirDao;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == kurirFrame.getButtonSetujui()) {
            int row = kurirFrame.getSelectedKurirRow();
            if(row == -1) {
                kurirFrame.showErrorMessage("Pilih kurir terlebih dahulu!");
                return;
            }

            if(kurirFrame.getSelectedKurirStatusRegistrasi().equals("Disetujui")) {
                kurirFrame.showErrorMessage("Kurir sudah disetujui!");
                return;
            } else if(kurirFrame.getSelectedKurirStatusRegistrasi().equals("Ditolak")) {
                kurirFrame.showErrorMessage("Kurir sudah ditolak!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(kurirFrame, "Apakah anda yakin ingin menyetujui kurir ini?",
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.NO_OPTION) {
                kurirFrame.showInfoMessage("Proses dibatalkan!");
                return;
            }

            int col = 3;

            kurir = kurirDao.findById(kurirFrame.getSelectedKurirId());
            kurir.setStatusRegistrasi("Disetujui");
            kurirDao.update(kurir);
            kurirFrame.updateKurir(row, col, kurir);
            kurirFrame.showSuccessMessage("Kurir berhasil disetujui!");
        }
    }

}
