package mk.ukim.finki.wp.lab.model.converter;

import mk.ukim.finki.wp.lab.model.TeacherFullName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TeacherNameConverter implements AttributeConverter<TeacherFullName, String> {
    @Override
    public String convertToDatabaseColumn(TeacherFullName teacherFullName) {
        if(teacherFullName==null)
            return null;
        StringBuilder sb = new StringBuilder();
        if(teacherFullName.getName()!=null && !teacherFullName.getName().isEmpty()){
            sb.append(teacherFullName.getName());
            sb.append(" ");
        }
        if(teacherFullName.getSurname()!=null && !teacherFullName.getSurname().isEmpty()){
            sb.append(teacherFullName.getSurname());
        }

        return sb.toString();
    }

    @Override
    public TeacherFullName convertToEntityAttribute(String s) {
        if(s==null || s.isEmpty())
            return null;
        String[] split = s.split(" ");
        if(split.length<=1)
            return null;
        return new TeacherFullName(split[0], split[1]);
    }
}
