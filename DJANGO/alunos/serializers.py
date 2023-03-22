from rest_framework import serializers
from .models import Aluno

class AlunoSerializers(serializers.ModelSerializer):
    class Meta:
        model = Aluno
        fields = '__all__'