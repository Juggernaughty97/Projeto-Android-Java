package com.projecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Dados> dadosArrayList;

    public MyCustomAdapter(Context context, ArrayList<Dados> dadosArrayList)
    {
        this.context = context;
        this.dadosArrayList = dadosArrayList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return this.dadosArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return dadosArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        Dados dados = dadosArrayList.get(position);

        if (view == null)
        {
            view = this.layoutInflater.inflate(R.layout.dadoslistview, viewGroup, false);
        }

        TextView textViewNumList = view.findViewById(R.id.textViewIdList);
        TextView textViewNomeList = view.findViewById(R.id.textViewNameList);
        TextView textViewTelList = view.findViewById(R.id.textViewPhoneList);

        textViewNumList.setText(String.valueOf(dados.getNumero()));
        textViewNomeList.setText(dados.getNome());
        textViewTelList.setText(dados.getTelefone());

        return view;
    }
}
