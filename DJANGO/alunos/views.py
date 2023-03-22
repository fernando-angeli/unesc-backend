from django.shortcuts import render
from rest_framework import viewsets
from rest_framework.permissions import IsAuthenticated

from .serializers import AlunoSerializers
from alunos.models import Aluno

class AlunoViewSet(viewsets.ModelViewSet):

    permission_classes = (IsAuthenticated,)

    serializer_class = AlunoSerializers
    queryset = Aluno.objects.all()