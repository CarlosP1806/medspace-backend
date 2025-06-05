package com.medspace.infrastructure.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SpecialtyDetector {
    private static final Map<String, String> SPECIALTY_KEYWORDS = new HashMap<>();
    private static final String DEFAULT_SPECIALTY = "Clínica General";
    private static final Pattern GENERAL_MEDICINE_PATTERN = Pattern.compile(
            "\\b(doctor|dra|dr|médico|medica|consulta|clínica|atención|servicios|salud|médica|medico|general|familiar|primaria|integral|básica|preventiva|comunidad|comunitaria|especialidades|especialidad|múltiples|múltiple|varias|varios|varios servicios|varias especialidades|médicos|médicas|doctores|doctoras|atención médica|servicios médicos|consulta médica|clínica médica|atención integral|servicios integrales|atención básica|servicios básicos|atención preventiva|servicios preventivos|atención comunitaria|servicios comunitarios|atención familiar|servicios familiares|atención primaria|servicios primarios|atención general|servicios generales|atención múltiple|servicios múltiples|atención variada|servicios variados|atención diversa|servicios diversos|atención completa|servicios completos|atención total|servicios totales|atención global|servicios globales|atención universal|servicios universales|atención generalizada|servicios generalizados|atención amplia|servicios amplios|atención extensa|servicios extensos|atención abarcadora|servicios abarcadores|atención comprensiva|servicios comprensivos|atención exhaustiva|servicios exhaustivos|atención integral|servicios integrales|atención holística|servicios holísticos|atención completa|servicios completos|atención total|servicios totales|atención global|servicios globales|atención universal|servicios universales|atención generalizada|servicios generalizados|atención amplia|servicios amplios|atención extensa|servicios extensos|atención abarcadora|servicios abarcadores|atención comprensiva|servicios comprensivos|atención exhaustiva|servicios exhaustivos|atención integral|servicios integrales|atención holística|servicios holísticos)\\b");

    static {
        // Pediatría
        SPECIALTY_KEYWORDS.put("pediatría", "Pediatría");
        SPECIALTY_KEYWORDS.put("pediatra", "Pediatría");
        SPECIALTY_KEYWORDS.put("niños", "Pediatría");
        SPECIALTY_KEYWORDS.put("infantil", "Pediatría");
        SPECIALTY_KEYWORDS.put("pediátrico", "Pediatría");
        SPECIALTY_KEYWORDS.put("clínica infantil", "Pediatría");
        SPECIALTY_KEYWORDS.put("atención infantil", "Pediatría");
        SPECIALTY_KEYWORDS.put("pediátrica", "Pediatría");
        SPECIALTY_KEYWORDS.put("pediátricas", "Pediatría");
        SPECIALTY_KEYWORDS.put("pediátricos", "Pediatría");
        SPECIALTY_KEYWORDS.put("pediatras", "Pediatría");
        SPECIALTY_KEYWORDS.put("niño", "Pediatría");
        SPECIALTY_KEYWORDS.put("niña", "Pediatría");
        SPECIALTY_KEYWORDS.put("bebé", "Pediatría");
        SPECIALTY_KEYWORDS.put("bebés", "Pediatría");
        SPECIALTY_KEYWORDS.put("infantes", "Pediatría");
        SPECIALTY_KEYWORDS.put("infancia", "Pediatría");
        SPECIALTY_KEYWORDS.put("clínica de niños", "Pediatría");
        SPECIALTY_KEYWORDS.put("atención pediátrica", "Pediatría");
        SPECIALTY_KEYWORDS.put("servicios pediátricos", "Pediatría");
        SPECIALTY_KEYWORDS.put("consulta pediátrica", "Pediatría");
        SPECIALTY_KEYWORDS.put("especialidad pediátrica", "Pediatría");
        SPECIALTY_KEYWORDS.put("especialidades pediátricas", "Pediatría");

        // Ginecología
        SPECIALTY_KEYWORDS.put("ginecología", "Ginecología");
        SPECIALTY_KEYWORDS.put("ginecólogo", "Ginecología");
        SPECIALTY_KEYWORDS.put("ginecóloga", "Ginecología");
        SPECIALTY_KEYWORDS.put("mujer", "Ginecología");
        SPECIALTY_KEYWORDS.put("obstetricia", "Ginecología");
        SPECIALTY_KEYWORDS.put("maternidad", "Ginecología");
        SPECIALTY_KEYWORDS.put("clínica de la mujer", "Ginecología");
        SPECIALTY_KEYWORDS.put("atención femenina", "Ginecología");
        SPECIALTY_KEYWORDS.put("ginecológica", "Ginecología");
        SPECIALTY_KEYWORDS.put("ginecológicas", "Ginecología");
        SPECIALTY_KEYWORDS.put("ginecológicos", "Ginecología");
        SPECIALTY_KEYWORDS.put("ginecólogas", "Ginecología");
        SPECIALTY_KEYWORDS.put("ginecólogos", "Ginecología");
        SPECIALTY_KEYWORDS.put("obstetra", "Ginecología");
        SPECIALTY_KEYWORDS.put("obstetras", "Ginecología");
        SPECIALTY_KEYWORDS.put("mujeres", "Ginecología");
        SPECIALTY_KEYWORDS.put("femenino", "Ginecología");
        SPECIALTY_KEYWORDS.put("femenina", "Ginecología");
        SPECIALTY_KEYWORDS.put("clínica femenina", "Ginecología");
        SPECIALTY_KEYWORDS.put("atención ginecológica", "Ginecología");
        SPECIALTY_KEYWORDS.put("servicios ginecológicos", "Ginecología");
        SPECIALTY_KEYWORDS.put("consulta ginecológica", "Ginecología");
        SPECIALTY_KEYWORDS.put("especialidad ginecológica", "Ginecología");
        SPECIALTY_KEYWORDS.put("especialidades ginecológicas", "Ginecología");

        // Odontología
        SPECIALTY_KEYWORDS.put("odontología", "Odontología");
        SPECIALTY_KEYWORDS.put("dentista", "Odontología");
        SPECIALTY_KEYWORDS.put("dental", "Odontología");
        SPECIALTY_KEYWORDS.put("clínica dental", "Odontología");
        SPECIALTY_KEYWORDS.put("consultorio dental", "Odontología");
        SPECIALTY_KEYWORDS.put("atención dental", "Odontología");
        SPECIALTY_KEYWORDS.put("ortodoncista", "Odontología");
        SPECIALTY_KEYWORDS.put("endodoncista", "Odontología");
        SPECIALTY_KEYWORDS.put("odontológica", "Odontología");
        SPECIALTY_KEYWORDS.put("odontológicas", "Odontología");
        SPECIALTY_KEYWORDS.put("odontológicos", "Odontología");
        SPECIALTY_KEYWORDS.put("dentistas", "Odontología");
        SPECIALTY_KEYWORDS.put("dental", "Odontología");
        SPECIALTY_KEYWORDS.put("dentales", "Odontología");
        SPECIALTY_KEYWORDS.put("clínica odontológica", "Odontología");
        SPECIALTY_KEYWORDS.put("atención odontológica", "Odontología");
        SPECIALTY_KEYWORDS.put("servicios odontológicos", "Odontología");
        SPECIALTY_KEYWORDS.put("consulta odontológica", "Odontología");
        SPECIALTY_KEYWORDS.put("especialidad odontológica", "Odontología");
        SPECIALTY_KEYWORDS.put("especialidades odontológicas", "Odontología");
        SPECIALTY_KEYWORDS.put("ortodoncia", "Odontología");
        SPECIALTY_KEYWORDS.put("endodoncia", "Odontología");
        SPECIALTY_KEYWORDS.put("periodoncia", "Odontología");
        SPECIALTY_KEYWORDS.put("prostodoncia", "Odontología");
        SPECIALTY_KEYWORDS.put("implantología", "Odontología");
        SPECIALTY_KEYWORDS.put("cirugía dental", "Odontología");
        SPECIALTY_KEYWORDS.put("cirugía maxilofacial", "Odontología");

        // Oftalmología
        SPECIALTY_KEYWORDS.put("oftalmología", "Oftalmología");
        SPECIALTY_KEYWORDS.put("oftalmólogo", "Oftalmología");
        SPECIALTY_KEYWORDS.put("oftalmóloga", "Oftalmología");
        SPECIALTY_KEYWORDS.put("ojo", "Oftalmología");
        SPECIALTY_KEYWORDS.put("visual", "Oftalmología");
        SPECIALTY_KEYWORDS.put("clínica visual", "Oftalmología");
        SPECIALTY_KEYWORDS.put("atención visual", "Oftalmología");
        SPECIALTY_KEYWORDS.put("optometría", "Oftalmología");
        SPECIALTY_KEYWORDS.put("oftalmológica", "Oftalmología");
        SPECIALTY_KEYWORDS.put("oftalmológicas", "Oftalmología");
        SPECIALTY_KEYWORDS.put("oftalmológicos", "Oftalmología");
        SPECIALTY_KEYWORDS.put("oftalmólogas", "Oftalmología");
        SPECIALTY_KEYWORDS.put("oftalmólogos", "Oftalmología");
        SPECIALTY_KEYWORDS.put("ojos", "Oftalmología");
        SPECIALTY_KEYWORDS.put("visuales", "Oftalmología");
        SPECIALTY_KEYWORDS.put("clínica oftalmológica", "Oftalmología");
        SPECIALTY_KEYWORDS.put("atención oftalmológica", "Oftalmología");
        SPECIALTY_KEYWORDS.put("servicios oftalmológicos", "Oftalmología");
        SPECIALTY_KEYWORDS.put("consulta oftalmológica", "Oftalmología");
        SPECIALTY_KEYWORDS.put("especialidad oftalmológica", "Oftalmología");
        SPECIALTY_KEYWORDS.put("especialidades oftalmológicas", "Oftalmología");
        SPECIALTY_KEYWORDS.put("optometrista", "Oftalmología");
        SPECIALTY_KEYWORDS.put("optometristas", "Oftalmología");
        SPECIALTY_KEYWORDS.put("retina", "Oftalmología");
        SPECIALTY_KEYWORDS.put("glaucoma", "Oftalmología");
        SPECIALTY_KEYWORDS.put("cataratas", "Oftalmología");
        SPECIALTY_KEYWORDS.put("cirugía ocular", "Oftalmología");
        SPECIALTY_KEYWORDS.put("cirugía refractiva", "Oftalmología");

        // Dermatología
        SPECIALTY_KEYWORDS.put("dermatología", "Dermatología");
        SPECIALTY_KEYWORDS.put("dermatólogo", "Dermatología");
        SPECIALTY_KEYWORDS.put("dermatóloga", "Dermatología");
        SPECIALTY_KEYWORDS.put("piel", "Dermatología");
        SPECIALTY_KEYWORDS.put("clínica de la piel", "Dermatología");
        SPECIALTY_KEYWORDS.put("atención dermatológica", "Dermatología");
        SPECIALTY_KEYWORDS.put("dermatológica", "Dermatología");
        SPECIALTY_KEYWORDS.put("dermatológicas", "Dermatología");
        SPECIALTY_KEYWORDS.put("dermatológicos", "Dermatología");
        SPECIALTY_KEYWORDS.put("dermatólogas", "Dermatología");
        SPECIALTY_KEYWORDS.put("dermatólogos", "Dermatología");
        SPECIALTY_KEYWORDS.put("pieles", "Dermatología");
        SPECIALTY_KEYWORDS.put("clínica dermatológica", "Dermatología");
        SPECIALTY_KEYWORDS.put("atención dermatológica", "Dermatología");
        SPECIALTY_KEYWORDS.put("servicios dermatológicos", "Dermatología");
        SPECIALTY_KEYWORDS.put("consulta dermatológica", "Dermatología");
        SPECIALTY_KEYWORDS.put("especialidad dermatológica", "Dermatología");
        SPECIALTY_KEYWORDS.put("especialidades dermatológicas", "Dermatología");
        SPECIALTY_KEYWORDS.put("estética", "Dermatología");
        SPECIALTY_KEYWORDS.put("cosmética", "Dermatología");
        SPECIALTY_KEYWORDS.put("cirugía dermatológica", "Dermatología");
        SPECIALTY_KEYWORDS.put("cirugía estética", "Dermatología");
        SPECIALTY_KEYWORDS.put("cirugía cosmética", "Dermatología");

        // Traumatología
        SPECIALTY_KEYWORDS.put("traumatología", "Traumatología");
        SPECIALTY_KEYWORDS.put("traumatólogo", "Traumatología");
        SPECIALTY_KEYWORDS.put("traumatóloga", "Traumatología");
        SPECIALTY_KEYWORDS.put("ortopedia", "Traumatología");
        SPECIALTY_KEYWORDS.put("huesos", "Traumatología");
        SPECIALTY_KEYWORDS.put("fracturas", "Traumatología");
        SPECIALTY_KEYWORDS.put("lesiones", "Traumatología");
        SPECIALTY_KEYWORDS.put("traumatológica", "Traumatología");
        SPECIALTY_KEYWORDS.put("traumatológicas", "Traumatología");
        SPECIALTY_KEYWORDS.put("traumatológicos", "Traumatología");
        SPECIALTY_KEYWORDS.put("traumatólogas", "Traumatología");
        SPECIALTY_KEYWORDS.put("traumatólogos", "Traumatología");
        SPECIALTY_KEYWORDS.put("ortopédica", "Traumatología");
        SPECIALTY_KEYWORDS.put("ortopédicas", "Traumatología");
        SPECIALTY_KEYWORDS.put("ortopédicos", "Traumatología");
        SPECIALTY_KEYWORDS.put("ortopedista", "Traumatología");
        SPECIALTY_KEYWORDS.put("ortopedistas", "Traumatología");
        SPECIALTY_KEYWORDS.put("clínica traumatológica", "Traumatología");
        SPECIALTY_KEYWORDS.put("atención traumatológica", "Traumatología");
        SPECIALTY_KEYWORDS.put("servicios traumatológicos", "Traumatología");
        SPECIALTY_KEYWORDS.put("consulta traumatológica", "Traumatología");
        SPECIALTY_KEYWORDS.put("especialidad traumatológica", "Traumatología");
        SPECIALTY_KEYWORDS.put("especialidades traumatológicas", "Traumatología");
        SPECIALTY_KEYWORDS.put("cirugía traumatológica", "Traumatología");
        SPECIALTY_KEYWORDS.put("cirugía ortopédica", "Traumatología");
        SPECIALTY_KEYWORDS.put("cirugía de huesos", "Traumatología");
        SPECIALTY_KEYWORDS.put("cirugía de fracturas", "Traumatología");
        SPECIALTY_KEYWORDS.put("cirugía de lesiones", "Traumatología");

        // Cardiología
        SPECIALTY_KEYWORDS.put("cardiología", "Cardiología");
        SPECIALTY_KEYWORDS.put("cardiólogo", "Cardiología");
        SPECIALTY_KEYWORDS.put("cardióloga", "Cardiología");
        SPECIALTY_KEYWORDS.put("corazón", "Cardiología");
        SPECIALTY_KEYWORDS.put("cardiaco", "Cardiología");
        SPECIALTY_KEYWORDS.put("clínica cardiológica", "Cardiología");
        SPECIALTY_KEYWORDS.put("cardiológica", "Cardiología");
        SPECIALTY_KEYWORDS.put("cardiológicas", "Cardiología");
        SPECIALTY_KEYWORDS.put("cardiológicos", "Cardiología");
        SPECIALTY_KEYWORDS.put("cardiólogas", "Cardiología");
        SPECIALTY_KEYWORDS.put("cardiólogos", "Cardiología");
        SPECIALTY_KEYWORDS.put("corazones", "Cardiología");
        SPECIALTY_KEYWORDS.put("cardiacos", "Cardiología");
        SPECIALTY_KEYWORDS.put("clínica cardiológica", "Cardiología");
        SPECIALTY_KEYWORDS.put("atención cardiológica", "Cardiología");
        SPECIALTY_KEYWORDS.put("servicios cardiológicos", "Cardiología");
        SPECIALTY_KEYWORDS.put("consulta cardiológica", "Cardiología");
        SPECIALTY_KEYWORDS.put("especialidad cardiológica", "Cardiología");
        SPECIALTY_KEYWORDS.put("especialidades cardiológicas", "Cardiología");
        SPECIALTY_KEYWORDS.put("cirugía cardíaca", "Cardiología");
        SPECIALTY_KEYWORDS.put("cirugía cardiovascular", "Cardiología");
        SPECIALTY_KEYWORDS.put("cirugía del corazón", "Cardiología");
        SPECIALTY_KEYWORDS.put("cirugía vascular", "Cardiología");
        SPECIALTY_KEYWORDS.put("cirugía torácica", "Cardiología");

        // Neurología
        SPECIALTY_KEYWORDS.put("neurología", "Neurología");
        SPECIALTY_KEYWORDS.put("neurólogo", "Neurología");
        SPECIALTY_KEYWORDS.put("neuróloga", "Neurología");
        SPECIALTY_KEYWORDS.put("cerebro", "Neurología");
        SPECIALTY_KEYWORDS.put("nervioso", "Neurología");
        SPECIALTY_KEYWORDS.put("clínica neurológica", "Neurología");
        SPECIALTY_KEYWORDS.put("neurológica", "Neurología");
        SPECIALTY_KEYWORDS.put("neurológicas", "Neurología");
        SPECIALTY_KEYWORDS.put("neurológicos", "Neurología");
        SPECIALTY_KEYWORDS.put("neurólogas", "Neurología");
        SPECIALTY_KEYWORDS.put("neurólogos", "Neurología");
        SPECIALTY_KEYWORDS.put("cerebros", "Neurología");
        SPECIALTY_KEYWORDS.put("nerviosos", "Neurología");
        SPECIALTY_KEYWORDS.put("clínica neurológica", "Neurología");
        SPECIALTY_KEYWORDS.put("atención neurológica", "Neurología");
        SPECIALTY_KEYWORDS.put("servicios neurológicos", "Neurología");
        SPECIALTY_KEYWORDS.put("consulta neurológica", "Neurología");
        SPECIALTY_KEYWORDS.put("especialidad neurológica", "Neurología");
        SPECIALTY_KEYWORDS.put("especialidades neurológicas", "Neurología");
        SPECIALTY_KEYWORDS.put("cirugía neurológica", "Neurología");
        SPECIALTY_KEYWORDS.put("cirugía del cerebro", "Neurología");
        SPECIALTY_KEYWORDS.put("cirugía del sistema nervioso", "Neurología");
        SPECIALTY_KEYWORDS.put("cirugía craneal", "Neurología");
        SPECIALTY_KEYWORDS.put("cirugía cerebral", "Neurología");

        // Gastroenterología
        SPECIALTY_KEYWORDS.put("gastroenterología", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("gastroenterólogo", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("gastroenteróloga", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("digestivo", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("estómago", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("intestino", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("gastroenterológica", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("gastroenterológicas", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("gastroenterológicos", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("gastroenterólogas", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("gastroenterólogos", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("digestivos", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("estómagos", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("intestinos", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("clínica gastroenterológica", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("atención gastroenterológica", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("servicios gastroenterológicos", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("consulta gastroenterológica", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("especialidad gastroenterológica", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("especialidades gastroenterológicas", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("cirugía gastroenterológica", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("cirugía digestiva", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("cirugía del estómago", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("cirugía del intestino", "Gastroenterología");
        SPECIALTY_KEYWORDS.put("cirugía abdominal", "Gastroenterología");

        // Endocrinología
        SPECIALTY_KEYWORDS.put("endocrinología", "Endocrinología");
        SPECIALTY_KEYWORDS.put("endocrinólogo", "Endocrinología");
        SPECIALTY_KEYWORDS.put("endocrinóloga", "Endocrinología");
        SPECIALTY_KEYWORDS.put("diabetes", "Endocrinología");
        SPECIALTY_KEYWORDS.put("tiroides", "Endocrinología");
        SPECIALTY_KEYWORDS.put("hormonas", "Endocrinología");
        SPECIALTY_KEYWORDS.put("endocrinológica", "Endocrinología");
        SPECIALTY_KEYWORDS.put("endocrinológicas", "Endocrinología");
        SPECIALTY_KEYWORDS.put("endocrinológicos", "Endocrinología");
        SPECIALTY_KEYWORDS.put("endocrinólogas", "Endocrinología");
        SPECIALTY_KEYWORDS.put("endocrinólogos", "Endocrinología");
        SPECIALTY_KEYWORDS.put("diabéticos", "Endocrinología");
        SPECIALTY_KEYWORDS.put("tiroides", "Endocrinología");
        SPECIALTY_KEYWORDS.put("hormonales", "Endocrinología");
        SPECIALTY_KEYWORDS.put("clínica endocrinológica", "Endocrinología");
        SPECIALTY_KEYWORDS.put("atención endocrinológica", "Endocrinología");
        SPECIALTY_KEYWORDS.put("servicios endocrinológicos", "Endocrinología");
        SPECIALTY_KEYWORDS.put("consulta endocrinológica", "Endocrinología");
        SPECIALTY_KEYWORDS.put("especialidad endocrinológica", "Endocrinología");
        SPECIALTY_KEYWORDS.put("especialidades endocrinológicas", "Endocrinología");
        SPECIALTY_KEYWORDS.put("cirugía endocrinológica", "Endocrinología");
        SPECIALTY_KEYWORDS.put("cirugía de la tiroides", "Endocrinología");
        SPECIALTY_KEYWORDS.put("cirugía hormonal", "Endocrinología");
        SPECIALTY_KEYWORDS.put("cirugía metabólica", "Endocrinología");
        SPECIALTY_KEYWORDS.put("cirugía endocrina", "Endocrinología");

        // Psicología
        SPECIALTY_KEYWORDS.put("psicología", "Psicología");
        SPECIALTY_KEYWORDS.put("psicólogo", "Psicología");
        SPECIALTY_KEYWORDS.put("psicóloga", "Psicología");
        SPECIALTY_KEYWORDS.put("mental", "Psicología");
        SPECIALTY_KEYWORDS.put("terapia", "Psicología");
        SPECIALTY_KEYWORDS.put("psiquiatría", "Psicología");
        SPECIALTY_KEYWORDS.put("psiquiatra", "Psicología");
        SPECIALTY_KEYWORDS.put("psicológica", "Psicología");
        SPECIALTY_KEYWORDS.put("psicológicas", "Psicología");
        SPECIALTY_KEYWORDS.put("psicológicos", "Psicología");
        SPECIALTY_KEYWORDS.put("psicólogas", "Psicología");
        SPECIALTY_KEYWORDS.put("psicólogos", "Psicología");
        SPECIALTY_KEYWORDS.put("mentales", "Psicología");
        SPECIALTY_KEYWORDS.put("terapias", "Psicología");
        SPECIALTY_KEYWORDS.put("psiquiátrica", "Psicología");
        SPECIALTY_KEYWORDS.put("psiquiátricas", "Psicología");
        SPECIALTY_KEYWORDS.put("psiquiátricos", "Psicología");
        SPECIALTY_KEYWORDS.put("psiquiatras", "Psicología");
        SPECIALTY_KEYWORDS.put("clínica psicológica", "Psicología");
        SPECIALTY_KEYWORDS.put("atención psicológica", "Psicología");
        SPECIALTY_KEYWORDS.put("servicios psicológicos", "Psicología");
        SPECIALTY_KEYWORDS.put("consulta psicológica", "Psicología");
        SPECIALTY_KEYWORDS.put("especialidad psicológica", "Psicología");
        SPECIALTY_KEYWORDS.put("especialidades psicológicas", "Psicología");
        SPECIALTY_KEYWORDS.put("terapia psicológica", "Psicología");
        SPECIALTY_KEYWORDS.put("terapia mental", "Psicología");
        SPECIALTY_KEYWORDS.put("terapia psiquiátrica", "Psicología");
        SPECIALTY_KEYWORDS.put("terapia emocional", "Psicología");
        SPECIALTY_KEYWORDS.put("terapia conductual", "Psicología");

        // Nutrición
        SPECIALTY_KEYWORDS.put("nutrición", "Nutrición");
        SPECIALTY_KEYWORDS.put("nutriólogo", "Nutrición");
        SPECIALTY_KEYWORDS.put("nutrióloga", "Nutrición");
        SPECIALTY_KEYWORDS.put("dieta", "Nutrición");
        SPECIALTY_KEYWORDS.put("alimentación", "Nutrición");
        SPECIALTY_KEYWORDS.put("clínica nutricional", "Nutrición");
        SPECIALTY_KEYWORDS.put("nutricional", "Nutrición");
        SPECIALTY_KEYWORDS.put("nutricionales", "Nutrición");
        SPECIALTY_KEYWORDS.put("nutriólogas", "Nutrición");
        SPECIALTY_KEYWORDS.put("nutriólogos", "Nutrición");
        SPECIALTY_KEYWORDS.put("dietas", "Nutrición");
        SPECIALTY_KEYWORDS.put("alimentaciones", "Nutrición");
        SPECIALTY_KEYWORDS.put("clínica nutricional", "Nutrición");
        SPECIALTY_KEYWORDS.put("atención nutricional", "Nutrición");
        SPECIALTY_KEYWORDS.put("servicios nutricionales", "Nutrición");
        SPECIALTY_KEYWORDS.put("consulta nutricional", "Nutrición");
        SPECIALTY_KEYWORDS.put("especialidad nutricional", "Nutrición");
        SPECIALTY_KEYWORDS.put("especialidades nutricionales", "Nutrición");
        SPECIALTY_KEYWORDS.put("terapia nutricional", "Nutrición");
        SPECIALTY_KEYWORDS.put("terapia dietética", "Nutrición");
        SPECIALTY_KEYWORDS.put("terapia alimentaria", "Nutrición");
        SPECIALTY_KEYWORDS.put("terapia de nutrición", "Nutrición");
        SPECIALTY_KEYWORDS.put("terapia de alimentación", "Nutrición");

        // Fisioterapia
        SPECIALTY_KEYWORDS.put("fisioterapia", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("fisioterapeuta", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("rehabilitación", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("física", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("terapia física", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("fisioterapéutica", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("fisioterapéuticas", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("fisioterapéuticos", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("fisioterapeutas", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("rehabilitaciones", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("físicas", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("terapias físicas", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("clínica fisioterapéutica", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("atención fisioterapéutica", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("servicios fisioterapéuticos", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("consulta fisioterapéutica", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("especialidad fisioterapéutica", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("especialidades fisioterapéuticas", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("terapia fisioterapéutica", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("terapia de rehabilitación", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("terapia física", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("terapia de movimiento", "Fisioterapia");
        SPECIALTY_KEYWORDS.put("terapia de ejercicio", "Fisioterapia");

        // Urología
        SPECIALTY_KEYWORDS.put("urología", "Urología");
        SPECIALTY_KEYWORDS.put("urólogo", "Urología");
        SPECIALTY_KEYWORDS.put("uróloga", "Urología");
        SPECIALTY_KEYWORDS.put("próstata", "Urología");
        SPECIALTY_KEYWORDS.put("riñón", "Urología");
        SPECIALTY_KEYWORDS.put("urinario", "Urología");
        SPECIALTY_KEYWORDS.put("urológica", "Urología");
        SPECIALTY_KEYWORDS.put("urológicas", "Urología");
        SPECIALTY_KEYWORDS.put("urológicos", "Urología");
        SPECIALTY_KEYWORDS.put("urólogas", "Urología");
        SPECIALTY_KEYWORDS.put("urólogos", "Urología");
        SPECIALTY_KEYWORDS.put("próstatas", "Urología");
        SPECIALTY_KEYWORDS.put("riñones", "Urología");
        SPECIALTY_KEYWORDS.put("urinarios", "Urología");
        SPECIALTY_KEYWORDS.put("clínica urológica", "Urología");
        SPECIALTY_KEYWORDS.put("atención urológica", "Urología");
        SPECIALTY_KEYWORDS.put("servicios urológicos", "Urología");
        SPECIALTY_KEYWORDS.put("consulta urológica", "Urología");
        SPECIALTY_KEYWORDS.put("especialidad urológica", "Urología");
        SPECIALTY_KEYWORDS.put("especialidades urológicas", "Urología");
        SPECIALTY_KEYWORDS.put("cirugía urológica", "Urología");
        SPECIALTY_KEYWORDS.put("cirugía de próstata", "Urología");
        SPECIALTY_KEYWORDS.put("cirugía de riñón", "Urología");
        SPECIALTY_KEYWORDS.put("cirugía urinaria", "Urología");
        SPECIALTY_KEYWORDS.put("cirugía renal", "Urología");

        // Otorrinolaringología
        SPECIALTY_KEYWORDS.put("otorrinolaringología", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("otorrino", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("oído", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("nariz", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("garganta", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("audición", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("otorrinolaringológica", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("otorrinolaringológicas", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("otorrinolaringológicos", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("otorrinos", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("oídos", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("narices", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("gargantas", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("audiciones", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("clínica otorrinolaringológica", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("atención otorrinolaringológica", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("servicios otorrinolaringológicos", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("consulta otorrinolaringológica", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("especialidad otorrinolaringológica", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("especialidades otorrinolaringológicas", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("cirugía otorrinolaringológica", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("cirugía de oído", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("cirugía de nariz", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("cirugía de garganta", "Otorrinolaringología");
        SPECIALTY_KEYWORDS.put("cirugía de audición", "Otorrinolaringología");

        // Alergología
        SPECIALTY_KEYWORDS.put("alergología", "Alergología");
        SPECIALTY_KEYWORDS.put("alergólogo", "Alergología");
        SPECIALTY_KEYWORDS.put("alergóloga", "Alergología");
        SPECIALTY_KEYWORDS.put("alergias", "Alergología");
        SPECIALTY_KEYWORDS.put("inmunología", "Alergología");
        SPECIALTY_KEYWORDS.put("alergológica", "Alergología");
        SPECIALTY_KEYWORDS.put("alergológicas", "Alergología");
        SPECIALTY_KEYWORDS.put("alergológicos", "Alergología");
        SPECIALTY_KEYWORDS.put("alergólogas", "Alergología");
        SPECIALTY_KEYWORDS.put("alergólogos", "Alergología");
        SPECIALTY_KEYWORDS.put("alergia", "Alergología");
        SPECIALTY_KEYWORDS.put("inmunológica", "Alergología");
        SPECIALTY_KEYWORDS.put("inmunológicas", "Alergología");
        SPECIALTY_KEYWORDS.put("inmunológicos", "Alergología");
        SPECIALTY_KEYWORDS.put("clínica alergológica", "Alergología");
        SPECIALTY_KEYWORDS.put("atención alergológica", "Alergología");
        SPECIALTY_KEYWORDS.put("servicios alergológicos", "Alergología");
        SPECIALTY_KEYWORDS.put("consulta alergológica", "Alergología");
        SPECIALTY_KEYWORDS.put("especialidad alergológica", "Alergología");
        SPECIALTY_KEYWORDS.put("especialidades alergológicas", "Alergología");
        SPECIALTY_KEYWORDS.put("terapia alergológica", "Alergología");
        SPECIALTY_KEYWORDS.put("terapia inmunológica", "Alergología");
        SPECIALTY_KEYWORDS.put("terapia de alergias", "Alergología");
        SPECIALTY_KEYWORDS.put("terapia de inmunidad", "Alergología");
        SPECIALTY_KEYWORDS.put("terapia de hipersensibilidad", "Alergología");

        // Reumatología
        SPECIALTY_KEYWORDS.put("reumatología", "Reumatología");
        SPECIALTY_KEYWORDS.put("reumatólogo", "Reumatología");
        SPECIALTY_KEYWORDS.put("reumatóloga", "Reumatología");
        SPECIALTY_KEYWORDS.put("articulaciones", "Reumatología");
        SPECIALTY_KEYWORDS.put("artritis", "Reumatología");
        SPECIALTY_KEYWORDS.put("reumatológica", "Reumatología");
        SPECIALTY_KEYWORDS.put("reumatológicas", "Reumatología");
        SPECIALTY_KEYWORDS.put("reumatológicos", "Reumatología");
        SPECIALTY_KEYWORDS.put("reumatólogas", "Reumatología");
        SPECIALTY_KEYWORDS.put("reumatólogos", "Reumatología");
        SPECIALTY_KEYWORDS.put("articulación", "Reumatología");
        SPECIALTY_KEYWORDS.put("artritis", "Reumatología");
        SPECIALTY_KEYWORDS.put("clínica reumatológica", "Reumatología");
        SPECIALTY_KEYWORDS.put("atención reumatológica", "Reumatología");
        SPECIALTY_KEYWORDS.put("servicios reumatológicos", "Reumatología");
        SPECIALTY_KEYWORDS.put("consulta reumatológica", "Reumatología");
        SPECIALTY_KEYWORDS.put("especialidad reumatológica", "Reumatología");
        SPECIALTY_KEYWORDS.put("especialidades reumatológicas", "Reumatología");
        SPECIALTY_KEYWORDS.put("terapia reumatológica", "Reumatología");
        SPECIALTY_KEYWORDS.put("terapia de articulaciones", "Reumatología");
        SPECIALTY_KEYWORDS.put("terapia de artritis", "Reumatología");
        SPECIALTY_KEYWORDS.put("terapia de reumatismo", "Reumatología");
        SPECIALTY_KEYWORDS.put("terapia de artrosis", "Reumatología");
    }

    public static String detectSpecialty(String clinicName) {
        if (clinicName == null || clinicName.trim().isEmpty()) {
            return DEFAULT_SPECIALTY;
        }

        String normalizedName = clinicName.toLowerCase().trim();

        // First check for specific specialties
        for (Map.Entry<String, String> entry : SPECIALTY_KEYWORDS.entrySet()) {
            if (normalizedName.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        // If no specific specialty is found, check if it matches the general medicine pattern
        if (GENERAL_MEDICINE_PATTERN.matcher(normalizedName).find()) {
            return DEFAULT_SPECIALTY;
        }

        // If no specific specialty is found, it's likely a general clinic
        return DEFAULT_SPECIALTY;
    }
}
