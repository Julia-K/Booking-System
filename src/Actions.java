import allComands.Requests;
import tableFrames.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Actions {

    public static void setDetailOrUpdateClient (Boolean update,JButton jButton, JTable jTable, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            ResultSet rs = null;
            try {
                rs = Requests.readById(id,"client");
                while (rs.next()) {
                    String name = rs.getString(2);
                    String last = rs.getString(3);
                    String email = rs.getString(4);
                    String password = rs.getString(5);
                    String birthDate = rs.getString(6);
                    new ClientDetailsFrame(update,id,name,last,email,password,birthDate).addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            try {
                                mw.reloadClients();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public static void setDetailOrUpdateAddress (Boolean update,JButton jButton, JTable jTable, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String country = jTable.getValueAt(row, 0).toString();
            String city = jTable.getValueAt(row, 1).toString();
            String postal = jTable.getValueAt(row, 2).toString();
            String street = jTable.getValueAt(row, 3).toString();
            String number = jTable.getValueAt(row, 4).toString();
            new AddressDetailsFrame(update,id,country,city,postal,street,number).addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        mw.reloadAddress();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        });
    }

    public static void setDetailOrUpdateFlight (Boolean update,JButton jButton, JTable jTable, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            ResultSet rs = null;
            try {
                rs = Requests.readById(id,"flight");
                rs.next();
                int depid = rs.getInt(2);
                int arrid = rs.getInt(3);
                int pilotId = rs.getInt(4);
                int planeId = rs.getInt(5);
                String depTime = rs.getString(6).split("\\.")[0];
                String depDate = rs.getString(7);
                String arrTime = rs.getString(8).split("\\.")[0];
                String arrDate = rs.getString(9);
                int price = rs.getInt(10);
                new FlightDetailsFrame(update, id, depid, arrid, pilotId, planeId, depTime, depDate, arrTime, arrDate, price).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            mw.reloadFlight();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public static void setDetailOrUpdateAirport (Boolean update,JButton jButton, JTable jTable, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String name = jTable.getValueAt(row, 0).toString();
            String code = jTable.getValueAt(row, 1).toString();
            String address = jTable.getValueAt(row, 2).toString();
            try {
                new AirportDetailsFrame(update, id,name,code,address).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            mw.reloadAirport();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void setDetailOrUpdatePilot (Boolean update,JButton jButton, JTable jTable, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            ResultSet rs = null;
            try {
                rs = Requests.readTableByRequest("select * from pilot where pilotID="+ id);
                rs.next();
                String first = rs.getString(2);
                String last = rs.getString(3);
                String date = rs.getString(4);
                int airlineId = rs.getInt(5);
                new PilotDetailsFrame(update,id,first,last,date,jTable.getValueAt(row,3).toString(),airlineId).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            mw.reloadPilot();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void setDetailAirline (JButton jButton, JTable jTable) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String name = jTable.getValueAt(row, 0).toString();
            String code = jTable.getValueAt(row, 1).toString();
            try {
                ResultSet rs = Requests.readTableByRequest("select brand,model,planes_quantity from plane_airline\n" +
                        "inner join airline on airlineID = plane_airline.airline_id\n" +
                        "inner join plane on planeID = plane_airline.plane_id\n" +
                        "where airline_id=" + id);
                List<String> x = getPlanesList(rs);
                String[] list = x.toArray(new String[0]);
                new AirlineDetailsFrame(name,code,list);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });
    }

    public static void setUpdateAirline(JButton jButton, JTable jTable, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String name = jTable.getValueAt(row, 0).toString();
            String code = jTable.getValueAt(row, 1).toString();
            new AirlineDetailsFrame(id,name,code).addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        mw.reloadAirline();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        });
    }
    public static void setDetailOrUpdatePlane (boolean update, JButton jButton, JTable jTable, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String brand = jTable.getValueAt(row, 0).toString();
            String model = jTable.getValueAt(row, 1).toString();
            try {
                new PlaneDetailsFrame(update, id,brand,model).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            mw.reloadPlane();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    public static void addPilotAction(JButton jButton, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            try {
                new PilotDetailsFrame().addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            mw.reloadPilot();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }



    public static void addPlaneAction(JButton jButton, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            new PlaneDetailsFrame().addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        mw.reloadPlane();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        });
    }

    public static void addAirlineAction(JButton jButton, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            try {
                new AirlineDetailsFrame().addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            mw.reloadAirline();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public static void addFlightAction(JButton jButton, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            try {
                new FlightDetailsFrame().addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            mw.reloadFlight();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public static void detailsPilot(JButton jButton, JTable jTable) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String first = jTable.getValueAt(row, 0).toString();
            String last = jTable.getValueAt(row, 1).toString();
            String date = jTable.getValueAt(row, 2).toString();
            try {
                ResultSet rs = Requests.readTableByRequest("select airline.name, airline.code from airline\n" +
                        "inner join pilot on pilot.airline_id = airline.airlineID\n" +
                        "where pilotID=" + id);
                while (rs.next()) {
                    String airline = rs.getString(1) + " (" + rs.getString(2) + ")";
                    //new PilotDetailsFrame(first,last, date, airline);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void setDetailOrUpdateClass(Boolean update,JButton jButton, JTable jTable, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            ResultSet rs = null;
            try {
                rs = Requests.readById(id,"class");
                while (rs.next()) {
                    String name = rs.getString(2);
                    String price = rs.getString(3);
                    new ClassDetailsFrame(update,id,name,price).addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            try {
                                mw.reloadClass();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public static void setDetailOrUpdateLuggage(Boolean update,JButton jButton, JTable jTable, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            ResultSet rs = null;
            try {
                rs = Requests.readById(id,"luggage");
                while (rs.next()) {
                    String name = rs.getString(2);
                    float price = rs.getFloat(3);
                    String height = rs.getString(4);
                    String weight = rs.getString(5);
                    new LuggageDetailsFrame(update,id,name,price,height,weight).addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            try {
                                mw.reloadLuggage();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }



    public static void setDeleteButtonAction(JButton jButton, String name, JTable jTable, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e-> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            Requests.deleteRow(id, name);
            switch (name) {
                case "client":
                    try {
                        mw.reloadClients();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "address":
                    try {
                        mw.reloadAddress();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } break;
                case "airport":
                    try {
                        mw.reloadAirport();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "plane":
                    try {
                        mw.reloadPlane();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } break;
                case "airline":
                    try {
                        mw.reloadAirline();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } break;
                case "pilot":
                    try {
                        mw.reloadPilot();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } break;
                case "flight":
                    try {
                        mw.reloadFlight();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } break;
                case "class":
                    try {
                        mw.reloadClass();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } break;
                case "luggage":
                    try {
                        mw.reloadLuggage();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                default:
            }
        });
    }

    public static void addLuggageAction(JButton jButton, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> new LuggageDetailsFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    mw.reloadLuggage();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }));
    }

    public static void addAddressAction(JButton jButton, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> new AddressDetailsFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    mw.reloadAddress();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }));
    }

    public static void addClientAction(JButton jButton, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e-> new ClientDetailsFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    mw.reloadClients();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }));
    }

    public static void addClassAction(JButton jButton, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            new ClassDetailsFrame().addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        mw.reloadClass();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
        });
    }

    public static void addAirportAction (JButton jButton, ManageWindow mw) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            try {
                new AirportDetailsFrame().addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            mw.reloadAirport();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static List<String> getPlanesList(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<>();
        while (rs.next()) {
            String x = rs.getString(1) + " - " + rs.getString(2) + "  (Quantity: " + rs.getInt(3) + ")";
            list.add(x);
        }
        return list;
    }

    public static JButton removeActions(JButton jButton) {
        for (ActionListener x : jButton.getActionListeners())
            jButton.removeActionListener(x);

        return jButton;
    }
}
