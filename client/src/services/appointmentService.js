import api from "../services/api";

const appointmentService = {
  getAll: async () => {
    try {
      const response = await api.get("/appointments");
      return response.data;
    } catch (error) {
      console.error("Error fetching appointments:", error);
      throw error;
    }
  },

  getAppointmentById: async (id) => {
    try {
      const response = await api.get(`/appointments/${id}`);
      return response.data;
    } catch (error) {
      console.error("Error fetching appointment details:", error);
      throw error;
    }
  },

  updateStatus: async (id, status) => {
    try {
      const response = await api.put(
        `/admins/appointments/${id}/status`,
        null,
        {
          params: { status },
        }
      );
      return response.data;
    } catch (error) {
      console.error("Error updating appointment status:", error);
      throw error;
    }
  },
};

export default appointmentService;
